package org.example;


import org.example.database.DatabaseManager;
import org.example.excel.ExcelManager;
import org.example.metadata.ColumnInfo;
import org.example.metadata.ConnectManager;
import org.example.metadata.DatabaseType;
import org.example.metadata.TableInfo;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        ArrayList<ArrayList<String>> data;
        ArrayList<String> tableNamesFromDB;
        ArrayList<ColumnInfo> columnsInfoFromTable;
        HashMap<String, ArrayList<ColumnInfo>> tableAndColumn;
        String host = "172.16.119.93";
        String id = "sol_test2";
        String pw = "geotwo";
        String schemaName = "SOL_TEST2";
//        String tableName = "SAC";
        String tableName = "SAC2";
        String readExcelPath = "C:\\Users\\GEOTWO\\Desktop\\유창차라라\\예술의전당error.xlsx";
//        String readExcelPath = "C:\\Users\\GEOTWO\\Desktop\\유창차라라\\예술의전당.xlsx";
        String uploadExcelPath = "C:\\Users\\GEOTWO\\Desktop\\유창차라라" + ".xlsx";
        DatabaseType type = DatabaseType.ORACLE;

        ConnectManager connectManager = ConnectManager.getInstance();
        connectManager.setHost(host);
        connectManager.setId(id);
        connectManager.setPw(pw);

        connectManager.connectDB(type);

        TableInfo tableInfo = new TableInfo();
        connectManager.setTableInfo(tableInfo,schemaName);
        tableNamesFromDB = tableInfo.getTableNames();

        System.out.println(tableNamesFromDB);


        ColumnInfo columnInfo = new ColumnInfo();
        connectManager.setTableAndColumn(tableInfo, columnInfo, schemaName, tableName);
        columnsInfoFromTable = columnInfo.getColumInfo(tableName);

        for(int i=0; i< columnsInfoFromTable.size(); i++) {
            System.out.println("Column Name : " + columnsInfoFromTable.get(i).getName()
                                + "  Column Type : " + columnsInfoFromTable.get(i).getType()
                                + "  Column Size : " + columnsInfoFromTable.get(i).getSize());
        }

        tableAndColumn = columnInfo.getTableAndColumn();

        ExcelManager excelManager = new ExcelManager();
        excelManager.setReadPath(readExcelPath);
        excelManager.setUploadPath(uploadExcelPath);

        data = excelManager.readExcel();
        System.out.println(data);

        DatabaseManager dbManager = new DatabaseManager();
        dbManager.setTableName(tableName);
        dbManager.setColumnInfo(tableAndColumn.get(tableName));
        dbManager.insertIntoTable(data);
//        insertIntoTable(tableName, columnsInfoFromTable, data);
        excelManager.createExcelFromTable(dbManager, uploadExcelPath);

        connectManager.closeConnection();
    }
}