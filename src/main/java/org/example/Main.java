package org.example;


import org.example.metadata.Column;
import org.example.metadata.Connect;

import java.io.IOException;
import java.sql.SQLException;

import static org.example.excel.Create.createExcel;
import static org.example.metadata.Column.getColumn;
import static org.example.metadata.Tables.getTables;
import static org.example.database.Insert.insertToTable;
import static org.example.excel.Read.read;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        ArrayList<ArrayList<String>> data;
        ArrayList<Column> columnsInfo;

        String dbUrl = "jdbc:oracle:thin:@172.16.119.93:1521:orcl";
        String id = "sol_test2";
        String pw = "geotwo";
        String schemaName = "SOL_TEST2";
        String tableName = "SAC";


        Connect connectOracle = new Connect(dbUrl,id,pw);
        connectOracle.connectDB();

        getTables(connectOracle.conn,schemaName);
        columnsInfo = getColumn(connectOracle.conn,schemaName,tableName);

        data = read();

        System.out.println(data);

        insertToTable(connectOracle.conn,tableName,data);
        createExcel(connectOracle.conn, columnsInfo);

        connectOracle.conn.close();
    }
}