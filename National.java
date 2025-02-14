import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class National extends Flight {

	static int c = 0;
	String type = "National";
void national() {}

	void input(Scanner s) {
		counter();
		fid = "F-" + (++c);
		System.out.println("enter no of seats in that flight");
		Seats = s.nextInt();
		System.out.println("enter Flight Status Rest / Start Flight / Reach Flight ");
		Status = s.next();
		ro.input(s);
		insert();
		filing();
		
	}
	public String toString() {
		String s = fid + "\t" + Status + "\t" + Seats + "\t" + type + "\t" + ro.getrid();
		return s;
	}

	void filing() {
		String path = "C:\\Users\\Haseeb's PC\\Desktop\\Project files\\Flight.txt";
		File file = new File(path);
		try {
			file.createNewFile();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			FileWriter fw = new FileWriter(path, true);
			fw.write(toString());
			fw.write("\r\n");
			fw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void changestatus(Scanner s, String id) {
		System.out.println("Enter new Flight Status");
		Status = s.next();
		update(id);
	}

	void display() {
		super.display();
		System.out.println(type);
	}

	void insert() {
		String s = "insert into Flight(Fid,Status,Seats,Type,rid) values ('" + fid + "','" + Status + "'," + Seats
				+ ",'" + type + "','" + ro.getrid() + "')";
		DBhandler db = new DBhandler();
		db.change(s);
	}

	void update(String id) {
		String s = "update Flight set Status='" + Status + "where Fid='" + id + "'";
		DBhandler db = new DBhandler();
		db.change(s);

	}
	void minusseats(String id) {
		int seat = 0;
		try {
			DBhandler db = new DBhandler();
			ResultSet result = db.getdata("select * from flight");

			while (result.next()) {
				String i = result.getString("Fid");
				if (i.equals(id))
					seat = result.getInt("Seats");
			}

			seat--;
			String s = "update Flight set Seats=" + seat + "where Fid='" + fid + "'";
			db.change(s);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	void counter() {
		String no =null;
		try {
			DBhandler db = new DBhandler();
			ResultSet result = db.getdata("select * from Flight");
			String n = null;
			while (result.next()) {

				n = result.getString("fid");
				no = n.substring(2);
			}
			int number = 0;
			if (n != null) {
				number = Integer.parseInt(no);

				c = number;
			}
		} catch (SQLException e) {
		}
	}

	void changeroute(Scanner s, String Rid) {
		ro.update(s, Rid);
	}

}
