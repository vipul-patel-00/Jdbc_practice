import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.Scanner;

class TestApp2 {
    public static void main(String[] args) throws SQLException {
        // step1 -> Load and register the Driver
        // Driver driver = new Driver();
        // DriverManager.registerDriver(driver);
        // System.out.println("Driver registered succesfully");

        Scanner sc = new Scanner(System.in);
        
        System.out.println("enter 1 to read data from the table");
        System.out.println("enter 2 to update into the table");
        System.out.println("enter 3 to delete data from table");
        System.out.println("enter 4 to create new table");
        int input = sc.nextInt();

        // step2 -> Establish the connection b/w java and Database
        // JDBC URL SYNTAX:: <mainprotocol>:<subprotocol>:<subname>
        String url = "jdbc:mysql://localhost:3306/enterprisejava";
        String username = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connection object is created:: " + connection);
        System.out.println("implementation class name of connection is :: " + connection.getClass().getName());

        // step3 -> create Statement object
        Statement statement = connection.createStatement();
        System.out.println("Statement object is created:: " + statement);

        // step4 -> send and execute query
        switch (input) {
            case 1:
                String sqlSelectQuery = "select sid,sname,sage,sadd from student";
                ResultSet resultset = statement.executeQuery(sqlSelectQuery);
                System.out.println("SID\t\tSNAME\t\tSAGE\t\tSADD");
                while (resultset.next()) {
                    Integer id = resultset.getInt(1);
                    String name = resultset.getString(2);
                    Integer age = resultset.getInt(3);
                    String add = resultset.getString(4);
                    System.out.println(id + "\t\t" + name + "\t\t" + age + "\t\t" + add);
                }
                break;
            case 2:
                String sqlUpdateQuery = "insert into student(sid,sname,sage,sadd) values(68,'yash',22,'khargone')";
                int noOfRows = statement.executeUpdate(sqlUpdateQuery);
                System.out.println("no of rows affected are:: " + noOfRows);
                break;
            case 3:
                String sqlDeleteQuery = "delete from student where sid=68";
                int noOfRows1 = statement.executeUpdate(sqlDeleteQuery);
                System.out.println("no of rows affected are:: " + noOfRows1);
                break;
            case 4:
                String sqlCreateQuery = "create table teachers(tid int,tname varchar(20),tsub varchar(20))";
                statement.execute(sqlCreateQuery);
        }

        // step5 -> processing ResultSet
        // System.out.println("SID\t\tSNAME\t\tSAGE\t\tSADD");
        // while (resultset.next()) {
        // Integer id = resultset.getInt(1);
        // String name = resultset.getString(2);
        // Integer age = resultset.getInt(3);
        // String add = resultset.getString(4);
        // System.out.println(id + "\t\t" + name + "\t\t" + age + "\t\t" + add);
        // }

        // step6 -> close the connection
        connection.close();
        System.out.println("colsing the connection");
    }
}