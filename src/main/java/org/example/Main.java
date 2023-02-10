package org.example;


import org.example.database.DatabaseManager;
import org.example.excel.ExcelManager;
import org.example.metadata.ColumnInfo;
import org.example.metadata.ConnectManager;
import org.example.metadata.DatabaseType;
import org.example.metadata.TableInfo;
import org.example.user.UserInfo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {


        ArrayList<ArrayList<String>> data;
        ArrayList<String> tableNamesFromDB;
        ArrayList<ColumnInfo> columnsInfoFromTable;


        String host = "172.16.119.93";
        String port = "1521";
        String sid = "orcl";

        String id = "sol_test2";
        String pw = "geotwo";

        String schemaName = "SOL_TEST2";
//        String tableName = "SAC";
        String tableName = "SAC2";

        String readExcelPath = "C:\\Users\\GEOTWO\\Desktop\\유창차라라\\예술의전당error.xlsx";
//        String readExcelPath = "C:\\Users\\GEOTWO\\Desktop\\유창차라라\\예술의전당.xlsx";
        String uploadExcelPath = "C:\\Users\\GEOTWO\\Desktop\\유창차라라" + ".xlsx";

        String dbType = "oracle"; //데이터베이스 이름 적기
        DatabaseType type = DatabaseType.valueOf(dbType.trim().toUpperCase());

        UserInfo dbUser = new UserInfo(id, pw);
        dbUser.setHost(host);
        dbUser.setPort(port);
        dbUser.setSid(sid);
        dbUser.setSchema(schemaName);
        dbUser.setTableNm(tableName);

        ConnectManager connMngr = new ConnectManager(dbUser);
        connMngr.connectDB(type);

        tableNamesFromDB = connMngr.getTableNameList();

        System.out.println(tableNamesFromDB);

        TableInfo table = connMngr.getTableInfo();
        columnsInfoFromTable = table.getColumnInfo();

        for(int i=0; i< columnsInfoFromTable.size(); i++) {
            System.out.println("Column Name : " + columnsInfoFromTable.get(i).getName()
                    + "  Column Type : " + columnsInfoFromTable.get(i).getType()
                    + "  Column Size : " + columnsInfoFromTable.get(i).getSize());
        }

        ExcelManager excelManager = new ExcelManager();
        excelManager.setReadPath(readExcelPath);
        excelManager.setUploadPath(uploadExcelPath);

        data = excelManager.readExcel();
        System.out.println(data);

        DatabaseManager dbManager = new DatabaseManager();
        connMngr.giveConnToDB(dbManager);
        dbManager.setTargetTable(table);

        dbManager.insertIntoTable(data);

        excelManager.createExcelFromTable(dbManager);

        connMngr.closeConnection();
    }
}