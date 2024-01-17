package com.example.demo.utilties;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelFileExportUtility {
    private final  static Logger logger = LoggerFactory.getLogger(ExcelFileExportUtility.class);

    public static ByteArrayInputStream generateExcelFile(String sheetName, Map<String, String> labelMap,
                                                         JSONObject dataList){
        logger.info("Generating export file---------------start()");
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet(sheetName);
            Row row = sheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Creating header
            int count = 0;
            for(String key: labelMap.keySet()){
                Cell cell = row.createCell(count);
                cell.setCellValue(labelMap.get(key));
                cell.setCellStyle(headerCellStyle);
                count++;
            }
            // Creating data rows for each object
            JSONArray list = (JSONArray) dataList.get(Constants.DATA);
            for(int i = 0; i < list.size(); i++) {
                JSONObject jsonObject = (JSONObject) list.get(i);
                //Set<String> keys = jsonObject.keySet();
                Set<String> keys = labelMap.keySet();
                Iterator<String> itr = keys.iterator();
                Row dataRow = sheet.createRow(i + 1);
                int cnt = 0;
                while(itr.hasNext()){
                    String key = itr.next();
                    if(jsonObject.containsKey(key)){
                        String value = (String) jsonObject.get(key);
                        dataRow.createCell(cnt).setCellValue(value);
                    }
                    cnt++;
                }
            }
            // Making size of column auto resize to fit with data
            int cnt = 0;
            for(int i = 0 ; i < labelMap.size(); i++){
                sheet.autoSizeColumn(cnt);
                cnt++;
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }catch(Exception ex) {
            ex.printStackTrace();
            logger.error("Error while generating the excel file");
        }
        return null;
    }
}
