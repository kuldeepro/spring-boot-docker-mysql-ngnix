package com.tenet.app.models.responses;

import org.springframework.stereotype.Component;

@Component
public class ConnectionDetailsResponse {

    String connectionUrl;
    int tableCount;
    int dbCount;

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public int getTableCount() {
        return tableCount;
    }

    public void setTableCount(int tableCount) {
        this.tableCount = tableCount;
    }

    public int getDbCount() {
        return dbCount;
    }

    public void setDbCount(int dbCount) {
        this.dbCount = dbCount;
    }
}
