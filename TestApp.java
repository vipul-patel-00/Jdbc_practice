import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class TestApp {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // step1. Load and register the Driver
        // Driver driver = new Driver();
        // DriverManager.registerDriver(driver);
        // Class.forName("com.mysql.cj.jdbc.Driver");

        // step 2.
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/enterprisejava", "root",
                "root");

        // step 3.
        // Statement statement = connection.createStatement();

        // step 4.
        String sqlQuery = "select * from student where sid=?";
        PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
        int sid = 67;
        pstmt.setInt(1, sid);
        ResultSet resultSet = pstmt.executeQuery();

        System.out.println("Sid\t\tSname\t\tSage\t\tSadd");
        // step 5.
        while (resultSet.next()) {
            int Sid = resultSet.getInt(1);
            String Sname = resultSet.getString(2);
            int Sage = resultSet.getInt(3);
            String Sadd = resultSet.getString(4);
            System.out.println(Sid + "\t\t" + Sname + "\t\t" + Sage + "\t\t" + Sadd);
        }

        // step 6.
        connection.close();
    }
}
