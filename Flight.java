import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Flight {
	String fid, Status = "rest";
	routes ro;
	int Seats;
	String ty;

	Flight() {
		ro = new routes();
	}

	Flight(String id, String st, int se, String t, String ri) {
		fid = id;
		Status = st;
		Seats = se;
		ty = t;
		ro = new routes();
		ro.rid = ri;
	}

	void input(Scanner s) {
	}

	void insert() {
	}

	void filing() {
	}

	void changestatus(Scanner s, String id) {
		System.out.println("Enter new Flight Status");
		Status = s.next();
		update(id);
	}

	void availableseats(String i) {
		int seat = 0;
		try {
			DBhandler db = new DBhandler();
			ResultSet result = db.getdata("select * from flight");

			while (result.next()) {
				String id = result.getString("Fid");
				if (i.equals(id))
					seat = result.getInt("Seats");
			}
			System.out.println("Available Seats = " + seat);
		} catch (SQLException e) {
		}

	}

	void display() {
		System.out.print(fid + "\t" + Seats + "\t" + ro.toString() + "\t");
	}

	String getfid() {
		return fid;
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

			String s;
			seat--;

			s = "update Flight set Seats=" + seat + "where Fid='" + id + "'";

			db.change(s);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	void update(String id) {
		String s = "update Flight set Status='" + Status + "'where Fid='" + id + "'";
		DBhandler db = new DBhandler();

		db.change(s);

	}

}
