package org.example;


import org.example.metadata.ColumnInfo;
import org.example.metadata.MetadataManager;
import org.example.metadata.TableInfo;

import java.io.IOException;
import java.sql.SQLException;

import static org.example.excel.CreateExcel.createExcelFromTable;
import static org.example.database.DataInput.insertIntoTable;
import static org.example.excel.ReadExcel.readExcel;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        ArrayList<ArrayList<String>> data;
        ArrayList<String> tableNamesFromDB;
        ArrayList<ColumnInfo> columnsInfoFromTable;
        HashMap<String, ArrayList<ColumnInfo>> tableAndColumn;
        String dbDriver = "oracle.jdbc.driver.OracleDriver";            //enum으로
        String dbUrl = "jdbc:oracle:thin:@172.16.119.93:1521:orcl";     //enum으로
        String id = "sol_test2";
        String pw = "geotwo";
        String schemaName = "SOL_TEST2";
        String tableName = "SAC";
        String readFilePath = "C:\\Users\\GEOTWO\\Desktop\\유창차라라\\예술의전당_공연 및 전시 입장객 현황_20220728.xlsx";
        String uploadFilePath = "C:\\Users\\GEOTWO\\Desktop\\유창차라라" + ".xlsx";

        MetadataManager manager = new MetadataManager(dbUrl,id,pw);
        manager.connectDB(dbDriver);

        TableInfo tablesInfo = new TableInfo();
        manager.setTableInfo(tablesInfo,schemaName);
        //tablesInfo.setTableNames(manager, schemaName);
        tableNamesFromDB = tablesInfo.getTableNames();
        System.out.println(tableNamesFromDB);

        ColumnInfo columnInfo = new ColumnInfo();
        manager.setColumnInfo(columnInfo,schemaName, tableName);
        //columnInfo.setTableAndColumn(manager, schemaName, tableName);
        columnsInfoFromTable = columnInfo.getColumInfo(tableName);

        for(int i=0; i< columnsInfoFromTable.size(); i++) {
            System.out.println("Column Name : " + columnsInfoFromTable.get(i).getName()
                                + "  Column Type : " + columnsInfoFromTable.get(i).getType()
                                + "  Column Size : " + columnsInfoFromTable.get(i).getSize());
        }

        tableAndColumn = columnInfo.getTableAndColumn();

        data = readExcel(readFilePath);
        System.out.println(data);

        insertIntoTable(manager.conn,tableName,data);

        createExcelFromTable(manager.conn, tableName, tableAndColumn, uploadFilePath);

        manager.conn.close();
    }
}