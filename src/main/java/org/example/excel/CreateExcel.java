package org.example.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.metadata.ColumnInfo;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.example.database.DataExtraction.selectAllFromTable;
//excel 만들기
public class CreateExcel {
//    private static void autoSizeColumns(Sheet sheet) {
//        int rowCount = sheet.getPhysicalNumberOfRows();
//        for (int i = 0; i < rowCount; i++) {
//            Row row = sheet.getRow(i);
//            Iterator<Cell> cellIterator = row.cellIterator();
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                int columnIndex = cell.getColumnIndex();
//                sheet.autoSizeColumn(columnIndex);
//            }
//        }
//    }
//    public static void createExcelFromTable(Connection conn, String tableName, ArrayList<ColumnInfo> columnsInfo,String uploadLocation)
//            throws SQLException, IOException {
//        ArrayList<ArrayList<String>> data = selectAllFromTable(conn,tableName,columnsInfo);
////        System.out.println(data);
//        XSSFWorkbook workBook = new XSSFWorkbook();
//        Sheet xSheet = workBook.createSheet("1");;
//        Row xRow = null;
//        Cell xCell = null;
//
//        File file = new File(uploadLocation);
//        FileOutputStream excel = new FileOutputStream(file);
//
//        xRow = xSheet.createRow(0);
//
//        for(int i =0; i< columnsInfo.size(); i++) {
//            xCell = xRow.createCell(i);
//            xSheet.setColumnWidth(i,10000);
//            //xSheet.setColumnWidth(i,columnsInfo.get(i).size * 256);
//            xCell.setCellValue(columnsInfo.get(i).name);
//        }
//
//        for(int i =0; i< data.size(); i++) {
//            xRow = xSheet.createRow(i+1);
//            for(int j =0; j<data.get(i).size(); j++) {
//                String tempData = data.get(i).get(j);
//                xCell = xRow.createCell(j);
//                xCell.setCellValue(tempData);
//            }
//        }
//        //autoSizeColumns(xSheet);
//
//        workBook.write(excel);
//        if(excel != null) {
//            excel.close();
//        }
//    }
    //db에서 가져온 데이터로 excel 만들기
    public static void createExcelFromTable(Connection conn, String tableName, HashMap<String, ArrayList<ColumnInfo>> tableAndColumn, String uploadLocation)
        throws SQLException, IOException {
        ArrayList<ArrayList<String>> data = selectAllFromTable(conn,tableName,tableAndColumn);
//        System.out.println(data);
        XSSFWorkbook workBook = new XSSFWorkbook();
        Sheet xSheet = workBook.createSheet("1");;
        Row xRow = null;
        Cell xCell = null;

        File file = new File(uploadLocation);
        FileOutputStream excel = new FileOutputStream(file);

        xRow = xSheet.createRow(0);

        for(int i =0; i< tableAndColumn.get(tableName).size(); i++) {
            xCell = xRow.createCell(i);
            xSheet.setColumnWidth(i,10000);
            //xSheet.setColumnWidth(i,columnsInfo.get(i).size * 256);
            xCell.setCellValue(tableAndColumn.get(tableName).get(i).getName());
        }

        for(int i =0; i< data.size(); i++) {
            xRow = xSheet.createRow(i+1);
            for(int j =0; j<data.get(i).size(); j++) {
                String tempData = data.get(i).get(j);
                xCell = xRow.createCell(j);
                xCell.setCellValue(tempData);
            }
        }
    //autoSizeColumns(xSheet);

        workBook.write(excel);
        if(excel != null) {
            excel.close();
        }
    }
}
