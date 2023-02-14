package com.excelToJavaObject.excelToJavaObject.controller;

import com.excelToJavaObject.excelToJavaObject.entities.Advisor;
import com.excelToJavaObject.excelToJavaObject.entities.User;
import com.excelToJavaObject.excelToJavaObject.helper.AdvisorExcel;
import com.excelToJavaObject.excelToJavaObject.helper.ExcelHelper;
import com.excelToJavaObject.excelToJavaObject.service.AdvisorService;
import com.excelToJavaObject.excelToJavaObject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdvisorService advisorService;

    @PostMapping("/advisor/upload")
    public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (AdvisorExcel.checkExcelFormat(file)) {
            advisorService.saveFile(file);
            return ResponseEntity.ok(Map.of("message", "File is uploaded "));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }

    @GetMapping("/advisor")
    public List<Advisor> getAllAdvisor() {
        return this.advisorService.getAllAdvisor();
    }



    @PostMapping("/user/upload")
    public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.checkExcelFormat(file)) {
            userService.saveFile(file);
            return ResponseEntity.ok(Map.of("message", "File is uploaded "));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }


    @GetMapping("/user")
    public List<User> getAllUser() {
        return this.userService.getAllUsers();
    }


}
