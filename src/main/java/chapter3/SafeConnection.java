package chapter3;

import annotation.ThreadSafe;
import com.sun.media.jfxmedia.locator.ConnectionHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ThreadSafe
public class SafeConnection {

    private static String DB_URL = "db_url";
    private static ThreadLocal<Connection> connectionHolder
            = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            try {
                return DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    };

    public static Connection getConnection() {
        return connectionHolder.get();
    }

}
