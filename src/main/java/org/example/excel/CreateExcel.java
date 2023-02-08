//package org.example.excel;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.example.metadata.ColumnInfo;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.*;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static org.example.database.DataExtraction.selectAllFromTable;
////excel 만들기
//public class CreateExcel {
//    //db에서 가져온 데이터로 excel 만들기
//    public static void createExcelFromTable(String tableName, HashMap<String, ArrayList<ColumnInfo>> tableAndColumn, String uploadExcelPath)
//        throws SQLException, IOException {
//
//        ArrayList<ArrayList<String>> data = selectAllFromTable(tableName,tableAndColumn);
//
//        XSSFWorkbook workBook = new XSSFWorkbook();
//        Sheet xSheet = workBook.createSheet("1");;
//        Row xRow;
//        Cell xCell;
//
//        File file = new File(uploadExcelPath);
//        FileOutputStream excel = new FileOutputStream(file);
//
//        xRow = xSheet.createRow(0);
//
//        //column 명 설정
//        for(int i =0; i< tableAndColumn.get(tableName).size(); i++) {
//            xCell = xRow.createCell(i);
//            xSheet.setColumnWidth(i,10000);
//            xCell.setCellValue(tableAndColumn.get(tableName).get(i).getName());
//        }
//
//        //엑셀파일에 데이터 쓰기
//        for(int i =0; i< data.size(); i++) {
//            xRow = xSheet.createRow(i+1);
//            for(int j =0; j<data.get(i).size(); j++) {
//                String tempData = data.get(i).get(j);
//                xCell = xRow.createCell(j);
//                xCell.setCellValue(tempData);
//            }
//        }
//        workBook.write(excel);
//        if(excel != null) {
//            excel.close();
//        }
//    }
//}
