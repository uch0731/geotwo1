package org.example.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.metadata.Column;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Create {
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
    public static void createExcel(Connection conn, ArrayList<Column> columnsInfo) throws SQLException, IOException {
        XSSFWorkbook workBook = new XSSFWorkbook();
        Sheet xSheet = workBook.createSheet("1");;
        Row xRow = null;
        Cell xCell = null;

        String query = "SELECT * FROM SOL_TEST2.SAC";
        PreparedStatement pstm = conn.prepareStatement(query);
        ResultSet rs = pstm.executeQuery();

        File file = new File("C:\\Users\\GEOTWO\\Desktop\\유창차라라" + ".xlsx");
        FileOutputStream excel = new FileOutputStream(file);

        xRow = xSheet.createRow(0);

        for(int i =0; i< columnsInfo.size(); i++) {
            xCell = xRow.createCell(i);
            xSheet.setColumnWidth(i,10000);
            //xSheet.setColumnWidth(i,columnsInfo.get(i).size * 256);
            xCell.setCellValue(columnsInfo.get(i).name);
        }
        int row = 1;

        while(rs.next()) {
            xRow = xSheet.createRow(row);

            for(int i =0; i< columnsInfo.size(); i++) {
                String getData = rs.getString(i+1);
                xCell = xRow.createCell(i);
                xCell.setCellValue(getData);
            }

            row++;
        }
        //autoSizeColumns(xSheet);
        rs.close();
        pstm.close();

        workBook.write(excel);
        if(excel != null) {
            excel.close();
        }
    }
}
