package com.example.education_centre.service.user;

import com.example.education_centre.dto.user.CheckUser;
import com.example.education_centre.model.Role;
import com.example.education_centre.model.User;
import com.example.education_centre.repository.UserRepository;
import com.example.education_centre.service.role.RoleService;
import com.example.education_centre.util.FileUploadUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    @Override
    public Page<User> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public CheckUser findById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        CheckUser checkUser = new CheckUser();
        assert user != null;
        checkUser.setId(user.getId());
        checkUser.setUserCode(user.getUserCode());
        checkUser.setName(user.getName());
        checkUser.setEmail(user.getEmail());
        checkUser.setPassword(user.getPassword());
        checkUser.setPhone(user.getPhone());
        checkUser.setAddress(user.getAddress());
        checkUser.setAvatarLink(user.getAvatar());
        checkUser.setStatus(user.getStatus());
        checkUser.setGender(user.getGender());
        checkUser.setDob(user.getDob());
        checkUser.setIdRole(user.getRole().getId());

        return checkUser;
    }

    @Override
    public List<User> findByUserCodes(String userCode) {
        return userRepository.findByUserCode(userCode);
    }

    @Override
    public List<User> getUsersByPageAndSize(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // Trang bắt đầu từ 0
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public List<User> searchUsers(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // Trang bắt đầu từ 0
        return userRepository.findByNameContainingIgnoreCaseOrUserCodeContainingIgnoreCase(keyword, keyword, pageable);
    }

    @Override
    public long getTotalUserCount() {
        return userRepository.count();
    }

    @Override
    public long getSearchResultCount(String keyword) {
        return userRepository.countByNameContainingIgnoreCaseOrUserCodeContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public boolean isUserCodeExists(String userCode) {
        return userRepository.existsByUserCode(userCode);
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(CheckUser data) {
        User user;

        // Kiểm tra xem userCode có tồn tại hay không
        List<User> existingUsers = userRepository.findByUserCode(data.getUserCode());
        if (!existingUsers.isEmpty()) {
            // Nếu tồn tại, lấy user đầu tiên
            user = existingUsers.get(0);
        } else {
            // Nếu không tồn tại, tạo mới
            user = new User();
        }

        Role role = roleService.findById(data.getIdRole());
        String address = data.getResultAddress() != null
                ? data.getResultAddress() + "|" + data.getAddress()
                : data.getAddress();

        //trường hợp update không cần tạo mật khẩu và không cần check email và userCode
        if(data.getPassword() != null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(data.getPassword());

            if (isUserCodeExists(data.getUserCode())) {
                throw new IllegalArgumentException("Mã người dùng đã được sử dụng");
            }
            if (isEmailExists(data.getEmail())) {
                throw new IllegalArgumentException("Email đã được sử dụng");
            }

            user.setPassword(encodedPassword);
        }

        String uploadDir = System.getProperty("user.dir") +  "/src/main/resources/static/assets/img/avatar/";
        String oldFileName = userRepository.findAvatarByUserCode(data.getUserCode());
        String newFileName = "";


        try {
            if (data.getAvatar() != null && !data.getAvatar().isEmpty()) {
                newFileName = FileUploadUtil.saveFileWithCustomName(data.getAvatar(), uploadDir);
                System.out.println("File uploaded successfully with new name: " + newFileName);

                if(oldFileName != null && !oldFileName.isEmpty()){
                    File oldFile = new File(uploadDir + "/" + oldFileName);
                    if (oldFile.exists()) {
                        boolean deleted = oldFile.delete();
                        if (deleted) {
                            System.out.println("Old file deleted: " + oldFileName);
                        } else {
                            System.out.println("Failed to delete old file: " + oldFileName);
                        }
                    }
                }

                user.setAvatar(newFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File upload failed: " + e.getMessage());
        }

        user.setUserCode(data.getUserCode());
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPhone(data.getPhone());
        user.setAddress(address);
        user.setStatus(data.getStatus());
        user.setGender(data.getGender());
        user.setDob(data.getDob());
        user.setRole(role);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String role = "ROLE_" + user.getRole().getName();

        List<String> authorities = new java.util.ArrayList<>(user.getRole().getPermissionRoles().stream()
                .map(permission -> permission.getPermission().getName())
                .toList());

        authorities.add(role);
//        System.out.println("Authorities: " + authorities);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities.toArray(new String[0]))
                .build();
    }
}
