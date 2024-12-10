package com.example.education_centre.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUploadUtil {
    public static String saveFileWithCustomName(MultipartFile file, String uploadDir) throws IOException {
        System.out.println("file: " + file.getOriginalFilename());
        // Lấy phần mở rộng của file (ví dụ .jpg, .png)
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // Tạo tên mới với mã random và thời gian
        String randomCode = generateRandomCode(3); // Tạo mã random 3 chữ
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); // Thời gian định dạng
        String newFileName = randomCode + "-" + timestamp + fileExtension;

        // Tạo thư mục lưu trữ nếu chưa tồn tại
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Đường dẫn đầy đủ để lưu file
        File destinationFile = new File(directory, newFileName);
        System.out.println(destinationFile);
        // Lưu file
        file.transferTo(destinationFile);

        return newFileName; // Trả về tên file mới
    }

    private static String generateRandomCode(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
