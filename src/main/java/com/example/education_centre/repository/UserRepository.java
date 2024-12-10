package com.example.education_centre.repository;

import com.example.education_centre.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByUserCode(String userCode);
    @Query("SELECT u.avatar FROM User u WHERE u.userCode = :userCode")
    String findAvatarByUserCode(@Param("userCode") String userCode);
    // Tìm kiếm người dùng theo tên hoặc mã người dùng
    List<User> findByNameContainingIgnoreCaseOrUserCodeContainingIgnoreCase(String name, String userCode, Pageable pageable);

    // Lấy tổng số người dùng (dùng trong phân trang)
    long count();

    // Lấy tổng số kết quả tìm kiếm (dùng trong tìm kiếm)
    long countByNameContainingIgnoreCaseOrUserCodeContainingIgnoreCase(String name, String userCode);
    boolean existsByUserCode(String userCode);
    boolean existsByEmail(String email);
}
