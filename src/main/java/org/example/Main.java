package org.example;


import org.example.metadata.ColumnInfo;
import org.example.metadata.ConnectManager;

import java.io.IOException;
import java.sql.SQLException;

import static org.example.excel.CreateExcel.createExcelFromTable;
import static org.example.metadata.ColumnInfo.getColumnInfo;
import static org.example.metadata.TableInfo.getTableInfo;
import static org.example.database.Insert.insertIntoTable;
import static org.example.excel.ReadExcel.readExcel;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        ArrayList<ArrayList<String>> data;
        ArrayList<String> tableInfo;
        //ArrayList<ColumnInfo> columnsInfo;
        HashMap<String, ArrayList<ColumnInfo>> tableAndColumn;
        String dbDriver = "oracle.jdbc.driver.OracleDriver";
        String dbUrl = "jdbc:oracle:thin:@172.16.119.93:1521:orcl";
        String id = "sol_test2";
        String pw = "geotwo";
        String schemaName = "SOL_TEST2";
        String tableName = "SAC";
        String readLocation = "C:\\Users\\GEOTWO\\Desktop\\유창차라라\\예술의전당_공연 및 전시 입장객 현황_20220728.xlsx";
        String uploadLocation = "C:\\Users\\GEOTWO\\Desktop\\유창차라라" + ".xlsx";

        ConnectManager connectOracle = new ConnectManager(dbUrl,id,pw);
        connectOracle.connectDB(dbDriver);

        tableInfo = getTableInfo(connectOracle.conn,schemaName);
        System.out.println(tableInfo);

        //columnsInfo = getColumnInfo(connectOracle.conn,schemaName,tableName);
        tableAndColumn = getColumnInfo(connectOracle.conn,schemaName,tableName);
        for(int i=0; i< tableAndColumn.get(tableName).size(); i++) {
            System.out.println("Column Name : " + tableAndColumn.get(tableName).get(i).name
                                + "  Column Type : " + tableAndColumn.get(tableName).get(i).type
                                + "  Column Size : " + tableAndColumn.get(tableName).get(i).size);
        }

        data = readExcel(readLocation);
        System.out.println(data);

        insertIntoTable(connectOracle.conn,tableName,data);

        createExcelFromTable(connectOracle.conn, tableName,tableAndColumn,uploadLocation);

        connectOracle.conn.close();
    }
}