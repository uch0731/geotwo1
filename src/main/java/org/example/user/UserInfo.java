package org.example.user;

public class UserInfo {
    private String host;
    private int port;
    private String sid;

    private String schema;
    private String tableNm;

    private String userNm;
    private String userPW;

    public UserInfo(String userNm, String userPW){
        this.userNm = userNm;
        this.userPW = userPW;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        setPort(Integer.parseInt(port));
    }

    public void setPort(int port) {
        // 파라미터 체크
        this.port = port;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getSid() {
        return sid;
    }

    public String getUserNm() {
        return userNm;
    }

    public String getUserPW() {
        return userPW;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTableNm() {
        return tableNm;
    }

    public void setTableNm(String tableNm) {
        this.tableNm = tableNm;
    }
}
