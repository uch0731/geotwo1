package org.example.metadata;

public enum DatabaseType {
    ORACLE("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@", ":1521:orcl"),
    MYSQL(" "," "," "),
    MSSQL(" "," "," ");

    private final String driver;
    private final String startUrl;
    private final String endUrl;

    DatabaseType(String driver, String startUrl, String endUrl){
        this.driver = driver;
        this.startUrl = startUrl;
        this.endUrl = endUrl;
    }

    public String getDriver(){
        return driver;
    }

    public String getStartUrl(){
        return startUrl;
    }

    public String getEndUrl(){
        return endUrl;
    }
}
