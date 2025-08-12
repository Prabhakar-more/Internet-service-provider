package com.isp.app.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final HikariDataSource ds;

    static {
        HikariConfig cfg = new HikariConfig();
        // <<-- UPDATE THESE WITH YOUR DB CREDENTIALS -->
        cfg.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        cfg.setUsername("SYSTEM");
        cfg.setPassword("PRABHAKAR");
        // Pool tuning
        cfg.setMaximumPoolSize(10);
        cfg.setMinimumIdle(2);
        cfg.setPoolName("ISP-HikariPool");
        cfg.addDataSourceProperty("cachePrepStmts", "true");
        cfg.addDataSourceProperty("prepStmtCacheSize", "250");
        cfg.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(cfg);
    }

    private Database() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
