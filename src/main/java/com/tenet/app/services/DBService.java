package com.tenet.app.services;

import com.tenet.app.exceptions.GlobalControllerExceptionHandler;
import com.tenet.app.models.responses.ConnectionDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class DBService {


    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);


    @Autowired
    DataSource dataSource;

    @Autowired
    ConnectionDetailsResponse connectionDetailsResponse;


    public ConnectionDetailsResponse getConnectionDetails() {
        LOGGER.info("Trying to resolve connection details.");
        try {
            int tableCount = 0;
            int dbCount = 0;
            Connection conn = dataSource.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            LOGGER.info("getConnection(): " + metaData.getConnection());
            LOGGER.info("connection metadata: " + conn.getClientInfo());

            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
            LOGGER.info("tables ResultSet:" + tables.toString());
            while (tables.next()) {
                tableCount++;
            }

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Show Databases");
            while (rs.next()) {
                dbCount++;
                LOGGER.info("db: " + rs.getString(1));
            }

            connectionDetailsResponse.setConnectionUrl(metaData.getURL());
            connectionDetailsResponse.setTableCount(tableCount);
            connectionDetailsResponse.setDbCount(dbCount);
        } catch (SQLException e) {
            LOGGER.info("Some SQL exception occured.");
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.info("Some Exception occured.");
        }
        return connectionDetailsResponse;

    }
}
