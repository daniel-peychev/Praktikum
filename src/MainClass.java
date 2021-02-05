import java.sql.Connection;

public class MainClass {
    public static void main(String[] args) {
    MyFrame board = new MyFrame();
    Connection con = DBConnection.getConnection();
    }
}