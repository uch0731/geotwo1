package org.example;


import org.example.metadata.ColumnInfo;
import org.example.metadata.Connect;
import org.example.metadata.TableInfo;

import java.io.IOException;
import java.sql.SQLException;

import static org.example.excel.CreateExcel.createExcelFromTable;
import static org.example.metadata.ColumnInfo.getColumnInfo;
import static org.example.metadata.TableInfo.getTableInfo;
import static org.example.database.Insert.insertToTable;
import static org.example.excel.ReadExcel.readExcel;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        ArrayList<ArrayList<String>> data;
        ArrayList<String> tableInfo;
        ArrayList<ColumnInfo> columnsInfo;
        String dbDriver = "oracle.jdbc.driver.OracleDriver";
        String dbUrl = "jdbc:oracle:thin:@172.16.119.93:1521:orcl";
        String id = "sol_test2";
        String pw = "geotwo";
        String schemaName = "SOL_TEST2";
        String tableName = "SAC";
        String readLocation = "C:\\Users\\GEOTWO\\Desktop\\유창차라라\\예술의전당_공연 및 전시 입장객 현황_20220728.xlsx";
        String uploadLocation = "C:\\Users\\GEOTWO\\Desktop\\유창차라라" + ".xlsx";

        Connect connectOracle = new Connect(dbUrl,id,pw);
        connectOracle.connectDB(dbDriver);

        tableInfo = getTableInfo(connectOracle.conn,schemaName);
        System.out.println(tableInfo);

        columnsInfo = getColumnInfo(connectOracle.conn,schemaName,tableName);
        for(int i=0; i< columnsInfo.size(); i++) {
            System.out.println("Column Name : " + columnsInfo.get(i).name + "  Column Type : " + columnsInfo.get(i).type
                                + "  Column Size : " + columnsInfo.get(i).size);
        }

        data = readExcel(readLocation);
        System.out.println(data);

        insertToTable(connectOracle.conn,tableName,data);

        createExcelFromTable(connectOracle.conn, tableName,columnsInfo,uploadLocation);

        connectOracle.conn.close();
    }
}