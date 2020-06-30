import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Testmain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/HSE_MS", "postgres", "postgresroot");
	Statement s = c.createStatement();
	//s.execute("");
	ResultSet r = s.executeQuery("SELECT\"personnel_number\",\"name\",\"surname\",\"position\", \"work_area\"FROM \"workers_personal_card\"");
	while(r.next()) {
		Integer number = r.getInt("personnel_number");
	String name = r.getString("name");
	String surname = r.getString("surname");
	String position = r.getString("position");
	String workarea = r.getString("work_area");
System.out.println(number + " " + name + " " + surname + " " + position + " " + workarea);
	}
r.close();
s.close();
c.close();
	
	}
}
