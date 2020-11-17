package internet_shop.service;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class ConnectBaseService {

    public static Connection connection;
    public static Statement statement;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/my_shop", "admin", "123456");
        statement = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
    }
}
