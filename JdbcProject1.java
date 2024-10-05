import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.Scanner;

class JdbcProject1 {

	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/enterprisejava";
		String username = "root";
		String password = "root";

		Connection connection = DriverManager.getConnection(url, username, password);
		Statement statement = connection.createStatement();

		// taking inputs according to the functions need to be performed
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1 to read data from table");
		System.out.println("Enter 2 to insert data into the table");
		System.out.println("Enter 3 to delete data from table");
		System.out.println("Enter 4 to create new table");

		int input = sc.nextInt();

		switch (input) {
			case 1:
				String sqlSelectQuery = "select sid,sname,sage,sadd from student";
				ResultSet resultset = statement.executeQuery(sqlSelectQuery);
				System.out.println("ResultSet object is created:: " + resultset);

				// step5 -> processing ResultSet
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
				System.out.println("Enter sid");
				int sid = sc.nextInt();
				System.out.println("Enter sname");
				String sname = sc.next();
				System.out.println("Enter sage");
				int sage = sc.nextInt();
				System.out.println("Enter sadd");
				String sadd = sc.next();

				sname = "'" + sname + "'";
				sadd = "'" + sadd + "'";

				String sqlInsertQuery = String.format("insert into student(sid,sname,sage,sadd) values(%d,%s,%d,%s)",
						sid, sname, sage, sadd);
				int noOfRows = statement.executeUpdate(sqlInsertQuery);
				System.out.println("no of rows affected :" + noOfRows);
				break;

			case 3:
				System.out.println("Enter sid to delete the entry");
				int sid1 = sc.nextInt();
				String sqlDeleteQuery = "delete from student where sid=" + sid1;
				statement.executeUpdate(sqlDeleteQuery);
				System.out.println("no of rows affecte : 1");
				break;

			case 4:
				System.out.println("Enter table_name and attributes to create table");
				System.out.println("example: student(sid int,sname varchar(20),sage int,sadd varchar(20))");
				sc.nextLine();
				String table = sc.nextLine();
				String sqlCreateQuery = String.format("create table %s", table);
				statement.execute(sqlCreateQuery);
				System.out.println("table created sucessfully");
				break;
		}

		connection.close();
		System.out.println("colsing the connection");

	}
}