import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Flight f = new Flight();
		National na = new National();
		International in = new International();
		customer c;
		ArrayList<Flight> fl = new ArrayList<Flight>();
		ArrayList<routes> ro = new ArrayList<routes>();
		ArrayList<customer> cu = new ArrayList<customer>();
		Scanner s = new Scanner(System.in);
		int ch;
		String id;
		storec(cu);
		storefr(fl, ro);
		do {
			System.out.println("press 1 to add customer" + "\npress 2 to add Flight" + "\npress 3 to change route"
					+ "\npress 4 to change flight status" + "\npress 5 to show all customers"
					+ "\npress 6 to show all flights" + "\npress 7 to show available seats"
					+ "\npress 8 to show all bookings" + "\npress 0 to Exit");
			ch = s.nextInt();
			switch (ch) {
			case 1:
				c = new customer();
				c.input(s);
				displayFlight();
				System.out.println("Enter Fid of Flight you want");
				id = s.next();
				try {
					DBhandler db = new DBhandler();
					ResultSet result = db.getdata("select * from Flight");
					while (result.next()) {

						String n = result.getString("Fid");
						if (n.equals(id)) {
							c.assignflight(n);
							f.minusseats(n);
						}
					}
					c.filing();

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				System.out.println("press 1  to add National Flight\npress 2 to add International Flight");
				int choice = s.nextInt();
				if (choice == 1) {
					f = new National();
					f.input(s);
				} else if (choice == 2) {
					f = new International();
					f.input(s);
				}
				break;
			case 3:
				displayFlight();
				System.out.println("Enter Flight id you want to change route");
				id = s.next();
				try {
					DBhandler db = new DBhandler();
					ResultSet result = db.getdata("select * from Flight");

					while (result.next()) {

						String n = result.getString("Fid");
						String n3 = result.getString("Type");
						String n4 = result.getString("rid");
						if (n.equals(id)) {
							if (n3.equals("National"))
								na.changeroute(s, n4);
							else if (n3.equals("International"))
								in.changeroute(s, n4);
						}
					}
				} catch (Exception e) {
				}
				break;
			case 4:
				displayFlight();
				System.out.println("Enter Flight id youy want to change status");
				id = s.next();
				try {
					DBhandler db = new DBhandler();
					ResultSet result = db.getdata("select fid from Flight");
					while (result.next()) {

						String n = result.getString("Fid");
						if (n.equals(id)) {
							f.changestatus(s, n);
							;
						}
					}

				} catch (Exception e) {
				}
				break;
			case 5:
				displayCustomer();
				break;
			case 6:

				displayFlight();
				break;
			case 7:
				displayFlight();
				System.out.println("enter Flight ID you want to see available seats");
				id = s.next();
				try {
					DBhandler db = new DBhandler();
					ResultSet result = db.getdata("select fid from Flight");
					while (result.next()) {
						String n = result.getString("Fid");
						if (n.equals(id)) {
							f.availableseats(id);
							;
						}
					}

				} catch (Exception e) {
				}

				break;
			case 8:
				displayALL();
				break;

			}

		} while (ch != 0);

	}

	static void displayFlight() {
		try {
			DBhandler db = new DBhandler();
			ResultSet result = db.getdata(
					"select f.Fid,f.Status,f.Seats,f.Type,r.Rid,r.source,r.destination,r.date,r.time from Flight f join routes r on f.rid=r.rid;");

			System.out.println("fid\tstatus\tseatstype\trid\tsource\tdestination\tdate\ttime");
			while (result.next()) {

				String n = result.getString("Fid");
				String n1 = result.getString("Status");
				int n2 = result.getInt("Seats");
				String n3 = result.getString("Type");
				String n4 = result.getString("Rid");
				String n5 = result.getString("source");
				String n6 = result.getString("destination");
				Date n7 = result.getDate("date");
				Time n8 = result.getTime("time");
				System.out.println(n + "\t" + n1 + "\t" + n2 + "\t" + n3 + "\t" + n4 + "\t" + n5 + "\t" + n6 + "\t" + n7
						+ "\t" + n8);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}

	static void displayCustomer() {
		try {
			DBhandler db = new DBhandler();
			ResultSet result = db.getdata("select * from CUSTOMER");

			while (result.next()) {
				String n11 = result.getString("TicketNo");
				String n = result.getString("Fname");
				String n1 = result.getString("Lname");
				String n2 = result.getString("PassportNumber");
				String n3 = result.getString("religion");
				String n4 = result.getString("city");
				String n5 = result.getString("nationality");
				String n6 = result.getString("Email");
				String n7 = result.getString("PhoneNo");
				String n8 = result.getString("Cnic");
				String n9 = result.getString("Fid");
				Date n10 = result.getDate("DOB");
				System.out.println(n11 + "\t" + n + "\t" + n1 + "\t" + n2 + "\t" + n3 + "\t" + n4 + "\t" + n5 + "\t"
						+ n6 + "\t" + n7 + "\t" + n8 + "\t" + n9 + "\t" + n10);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	static void displayALL() {
		try {
			DBhandler db = new DBhandler();
			ResultSet result = db.getdata(
					"select c.*,f.*,r.* from customer c join Flight f on c.Fid=f.fid  join routes r on f.rid=r.rid");

			while (result.next()) {

				String n = result.getString("Fname");
				String n1 = result.getString("Lname");
				String n2 = result.getString("PassportNumber");
				String n3 = result.getString("religion");
				String n4 = result.getString("city");
				String n5 = result.getString("nationality");
				String n6 = result.getString("Email");
				String n7 = result.getString("PhoneNo");
				String n8 = result.getString("Cnic");
				Date n10 = result.getDate("DOB");
				String n9 = result.getString("Fid");
				String n12 = result.getString("Status");
				int n13 = result.getInt("Seats");
				String n14 = result.getString("Type");
				String r1 = result.getString("Rid");
				String n16 = result.getString("Source");
				String n17 = result.getString("destination");
				Date n18 = result.getDate("date");
				Time n19 = result.getTime("time");

				System.out.println(n + "\t" + n1 + "\t" + n2 + "\t" + n3 + "\t" + n4 + "\t" + n5 + "\t" + n6 + "\t" + n7
						+ "\t" + n8 + "\t" + n10 + "\t" + n9 + "\t" + n12 + "\t" + n13 + "\t" + n14 + "\t" + r1 + "\t"
						+ n16 + "\t" + n17 + "\t" + n18 + "\t" + n19);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	static void storec(ArrayList<customer> cu) {
		try {
			DBhandler db = new DBhandler();
			ResultSet result = db.getdata("select * from CUSTOMER");

			while (result.next()) {
				String n11 = result.getString("TicketNo");
				String n = result.getString("Fname");
				String n1 = result.getString("Lname");
				String n2 = result.getString("PassportNumber");
				String n3 = result.getString("religion");
				String n4 = result.getString("city");
				String n5 = result.getString("nationality");
				String n6 = result.getString("Email");
				String n7 = result.getString("PhoneNo");
				String n8 = result.getString("Cnic");
				String n9 = result.getString("Fid");
				Date n10 = result.getDate("DOB");
				int n12 = result.getInt("cid");
				cu.add(new customer(n11, n12, n, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10));

			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}

	static void storefr(ArrayList<Flight> f, ArrayList<routes> r) {
		try {
			DBhandler db = new DBhandler();
			ResultSet result = db.getdata(
					"select f.Fid,f.Status,f.Seats,f.Type,r.Rid,r.source,r.destination,r.date,r.time from Flight f join routes r on f.rid=r.rid;");

			while (result.next()) {
				String n = result.getString("Fid");
				String n1 = result.getString("Status");
				int n2 = result.getInt("Seats");
				String n3 = result.getString("Type");
				String n4 = result.getString("Rid");
				String n5 = result.getString("source");
				String n6 = result.getString("destination");
				Date n7 = result.getDate("date");
				Time n8 = result.getTime("time");
				f.add(new Flight(n, n1, n2, n3, n4));
				r.add(new routes(n4, n5, n6, n7, n8));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}
}
