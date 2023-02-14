package com.excelToJavaObject.excelToJavaObject.service;

import com.excelToJavaObject.excelToJavaObject.Repository.AdvisorRepository;
import com.excelToJavaObject.excelToJavaObject.entities.Advisor;
import com.excelToJavaObject.excelToJavaObject.helper.AdvisorExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvisorService {

    @Autowired
    private AdvisorRepository advisorRepository;

    List<Advisor> advisorList = new ArrayList<>();

    public void saveFile(MultipartFile file) {
        try {
            Advisor advisor = AdvisorExcel.convertExcelToListOfAdvisor(file.getInputStream());
            advisorRepository.save(advisor);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Advisor> getAllAdvisor() {
        return advisorRepository.findAll();
    }
}
