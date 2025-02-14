import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBhandler {

	String constr="jdbc:sqlserver://DESKTOP-55FAVFV\\MSSQLSERVER;databaseName=OOPproject;user =sa;password=123;encrypt=false;";
	Connection conn;
	void change(String q)
	{
		try {
			conn =DriverManager.getConnection(constr);
			Statement statement = conn.createStatement();
			int rows = statement.executeUpdate(q);
			if(rows>0)
			System.out.println("Data Inserted Successfully");
			else
			System.out.println("Error Inserting Data");
			conn.close();
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			
		}
		
	}
	
	ResultSet getdata(String q)
	{
		ResultSet rs=null;
		try {
			conn =DriverManager.getConnection(constr);
			Statement statement = conn.createStatement();
			rs=statement.executeQuery(q);
			return rs;
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());}
		return rs;
	
	
	}
	
	
	
	
}
