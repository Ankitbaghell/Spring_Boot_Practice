package com.excelToJavaObject.excelToJavaObject.helper;

import com.excelToJavaObject.excelToJavaObject.entities.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file){
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            return true;
        else
            return false;
    }

    //convert excel to list of Users
    public static List<User> convertExcelToListOfUser(InputStream inputStream){
        List<User> userList = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();    //row-wise iterating over data sheet

            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cellId = 0;

                User user = new User();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cellId) {
                        case 0:
                            user.setUserId((int)cell.getNumericCellValue());
                            break;
                        case 1:
                            user.setFirstName(cell.getStringCellValue());
                            break;
                        case 2:
                            user.setLastName(cell.getStringCellValue());
                            break;
                        case 3:
                            user.setEmail(cell.getStringCellValue());
                            break;
                        case 4:
                            user.setCompany(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellId++;
                }
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}

