package com.temez.utilities.shared.mysql;


import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * @author Oneklo
 * @since 0.1
 */
@AllArgsConstructor
public class MySQL {

    private final String connectionUrl, database, username, password;
    private final int port;
    private final LoadingCache<Integer, Connection> cache = CacheBuilder
            .newBuilder().expireAfterAccess(10, TimeUnit.SECONDS)
            .removalListener((RemovalListener<Integer, Connection>) removalNotification -> {
                try {
                    if (removalNotification.getValue() != null) {
                        removalNotification.getValue().close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }).build(new CacheLoader<Integer, Connection>() {
                @Override
                public Connection load(Integer integer) {
                    return createConnection();
                }
            });

    public static Builder newBuilder() {
        return new Builder();
    }

    @SneakyThrows({SQLException.class, ExecutionException.class})
    public void update(String update, Object... objs) {
        Connection connection = cache.get(1);
        PreparedStatement p = connection.prepareStatement(update);
        setArguments(objs, p);
        p.execute();
        p.close();

    }

    @SneakyThrows({SQLException.class, ExecutionException.class})
    public ResultSet query(String query, Object... objs) {
        Connection connection = cache.get(1);
        PreparedStatement p = connection.prepareStatement(query);
        setArguments(objs, p);
        return p.executeQuery();
    }

    private void setArguments(Object[] objs, PreparedStatement p) throws SQLException {
        for (int i = 0; i < objs.length; i++) {
            Object obj = objs[i];
            if (obj instanceof String) {
                p.setString(i + 1, (String) obj);
            } else if (obj instanceof Integer) {
                p.setInt(i + 1, (Integer) obj);
            } else if (obj instanceof Date) {
                p.setDate(i + 1, (Date) obj);
            } else if (obj instanceof Timestamp) {
                p.setTimestamp(i + 1, (Timestamp) obj);
            } else if (obj instanceof Boolean) {
                p.setBoolean(i + 1, (Boolean) obj);
            } else if (obj instanceof Float) {
                p.setFloat(i + 1, (Float) obj);
            } else if (obj instanceof Double) {
                p.setDouble(i + 1, (Double) obj);
            } else if (obj instanceof Long) {
                p.setLong(i + 1, (Long) obj);
            }
        }
    }

    @SneakyThrows(SQLException.class)
    private Connection createConnection() {
        return DriverManager.getConnection("jdbc:mysql://" +
                        connectionUrl + ":" + port + "/" + database + "?useSSL=false",
                username, password);
    }

    @SneakyThrows(SQLException.class)
    public void disconnect() {
        if (createConnection() != null) {
            createConnection().close();
        }
    }

    public static final class Builder {

        private String connectionUrl, database, user, password;

        private Integer port;

        public Builder withUrl(String url) {
            this.connectionUrl = url;
            return this;
        }

        public Builder withDatabase(String database) {
            this.database = database;
            return this;
        }

        public Builder withUser(String user) {
            this.user = user;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withPort(Integer port) {
            this.port = port;
            return this;
        }

        public MySQL create() {
            Preconditions.checkNotNull(connectionUrl, "Connection URL is null");
            Preconditions.checkNotNull(database, "Database is null");
            Preconditions.checkNotNull(user, "Username is null");
            Preconditions.checkNotNull(password, "Password is null");
            Preconditions.checkNotNull(port, "Port is null");
            return new MySQL(connectionUrl, database, user, password, port);
        }
    }
}