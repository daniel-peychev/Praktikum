import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection conn = null;

    static Connection getConnection() {

        try {
            Class.forName("org.h2.Driver");
            try {
                conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/D:\\h2", "sa", "123");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return conn;
    }
}
