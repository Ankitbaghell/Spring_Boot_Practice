package com.excelToJavaObject.excelToJavaObject.service;

import com.excelToJavaObject.excelToJavaObject.entities.User;
import com.excelToJavaObject.excelToJavaObject.helper.ExcelHelper;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> userList = new ArrayList<>();

    public void saveFile(MultipartFile file) {

        try {
             userList = ExcelHelper.convertExcelToListOfUser(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        return userList;
    }


}
