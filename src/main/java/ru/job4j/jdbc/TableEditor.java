package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver_class"));
        String url = (properties.getProperty("url"));
        String login = (properties.getProperty("login"));
        String password = (properties.getProperty("password"));
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exists " + tableName + "(%s, %s);",
                    "id serial primary key",
                    "name varchar(255)"
            );
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "drop table if exists " + tableName;
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "alter table " + tableName + " add " + columnName + " " + type + ";";
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "alter table " + tableName + " drop column " + columnName + ";";
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "alter table " + tableName + " rename " + columnName + " to " + newColumnName + ";";
            statement.execute(sql);
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("./data/test2.properties"));
        TableEditor tableEditor = new TableEditor(prop);
        tableEditor.createTable("demo_table");
        System.out.println(getTableScheme(tableEditor.connection, "demo_table"));
        tableEditor.dropTable("demo_table");
        tableEditor.createTable("demo_table");
        tableEditor.addColumn("demo_table", "demo_column", "varchar(255)");
        System.out.println(getTableScheme(tableEditor.connection, "demo_table"));
        tableEditor.dropColumn("demo_table", "demo_column");
        System.out.println(getTableScheme(tableEditor.connection, "demo_table"));
        tableEditor.addColumn("demo_table", "demo_column", "varchar(255)");
        tableEditor.renameColumn("demo_table", "demo_column", "new_demo_column");
        System.out.println(getTableScheme(tableEditor.connection, "demo_table"));
    }
}