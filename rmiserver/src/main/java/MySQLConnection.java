import java.sql.*;

public class MySQLConnection {

    public static ResultSet createStatement(String sql) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi", "root", "test");
        Statement stmt = con.createStatement();
        return stmt.executeQuery(sql);
    }
}
