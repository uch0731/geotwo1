package org.example.metadata;

public enum DatabaseType {
    //1521 하고 orcl도 입력받아야함
    //총 유알엘을 스트링으로 만들고 입력받은 부분을 리플레이스
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
