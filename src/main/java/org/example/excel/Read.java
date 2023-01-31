package org.example.excel;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Read {
    public static ArrayList<ArrayList<String>> read(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\GEOTWO\\Desktop\\유창차라라\\예술의전당_공연 및 전시 입장객 현황_20220728.xlsx");
            XSSFWorkbook workBook = new XSSFWorkbook (fis);

            XSSFSheet sheet = workBook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();

            for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
                ArrayList<String> temp = new ArrayList<>();
                XSSFRow row = sheet.getRow(rowIndex);

                if (row != null) {
                    int cells = row.getPhysicalNumberOfCells();

                    for (int columnIndex = 0; columnIndex < cells; columnIndex++) {
                        XSSFCell cell = row.getCell(columnIndex);
                        String value = "";

                        switch (cell.getCellType()) {
                            case NUMERIC:
                                if( DateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                }
                                else {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case STRING:
                                value = cell.getStringCellValue() + "";
                                break;
                            case BLANK:
                                value = cell.getBooleanCellValue() + "";
                                break;
                            case ERROR:
                                value = cell.getErrorCellValue() + "";
                                break;
                        }
                        temp.add(value);
                        //System.out.print(value + "  ");
                    }
                    data.add(temp);
                    //System.out.println("");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
