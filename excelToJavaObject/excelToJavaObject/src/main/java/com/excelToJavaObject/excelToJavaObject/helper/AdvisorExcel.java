package com.excelToJavaObject.excelToJavaObject.helper;

import com.excelToJavaObject.excelToJavaObject.entities.Advisor;
import com.excelToJavaObject.excelToJavaObject.entities.Client;
import com.excelToJavaObject.excelToJavaObject.entities.Holding;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AdvisorExcel {
    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file){
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            return true;
        else
            return false;
    }

    //convert excel to list of Advisor and maintain hierarchy of advisor , client and holding
    public static Advisor convertExcelToListOfAdvisor(InputStream inputStream){
        List<Advisor> advisorList = new ArrayList<>();
        List<Client> clientList = new ArrayList<>();

        HashMap<Integer, List<Holding>> portfolioIdWithHoldings = new HashMap<>();

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

                Holding holding = new Holding();
                int portfolio = 0;

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cellId) {
                        case 0:
                            portfolio = (int)cell.getNumericCellValue();
                            break;
                        case 1:
                            holding.setQuantity((int)cell.getNumericCellValue());
                            break;
                        case 2:
                            holding.setSecuritySymbol(cell.getStringCellValue());
                            break;
                        case 3:
                            holding.setSecurityTypeCode(cell.getStringCellValue());
                            break;
                        case 4:
                            holding.setSecurityName(cell.getStringCellValue());
                            break;
                        case 5:
                            holding.setMarketValue(cell.getNumericCellValue());
                            break;
                        case 6:
                            holding.setMVAL(cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellId++;
                }
                if(portfolioIdWithHoldings.containsKey(portfolio)){
                    portfolioIdWithHoldings.get(portfolio).add(holding);
                }
                else {
                    portfolioIdWithHoldings.put(portfolio, new ArrayList<>());
                    portfolioIdWithHoldings.get(portfolio).add(holding);
                }

                // putIfAbsent , computeIfAbsent
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //now map has all info
        Set<Integer> keys = portfolioIdWithHoldings.keySet();
        keys.forEach(key -> {
            Client client = new Client();
            client.setClientAccountID(key);
            client.setHoldings(portfolioIdWithHoldings.get(key));
            clientList.add(client);
        });

        Advisor advisor = new Advisor();
        advisor.setClients(clientList);
        advisorList.add(advisor);
        return advisor;
    }
}
