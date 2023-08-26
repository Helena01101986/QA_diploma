package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();
    private static String url = System.getProperty("db.url");

    private SQLHelper() {
    }
    @SneakyThrows
    public static Connection qetConn() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows

    public static String getStatusForPayment() {
        String statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        return getResult(statusSQL);
    }

    @SneakyThrows
    public static String getStatusForCredit() {
        String statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        return getResult(statusSQL);
    }

    private static String getResult(String query) throws SQLException {
        String result = "";
        var runner = new QueryRunner();
        try (var conn = qetConn()) {
            result = runner.query(conn, query, new ScalarHandler<String>());
        }
        return result;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = qetConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}