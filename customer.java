import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class customer {

	String fname, lname, pid, religon, city, nationality, Email, phoneNo, cnic, ticketno;
	String fid;
	int CID;
	Date Dob;
	static int c = 0;

	customer() {
	}

	customer(String t, int ci, String fn, String ln, String pn, String re, String c, String na, String em, String ph,
			String cn, String id, Date d) {
		ticketno = t;
		fname = fn;
		lname = ln;
		pid = pn;
		religon = re;
		city = c;
		nationality = na;
		Email = em;
		phoneNo = ph;
		cnic = cn;
		CID = ci;
		Dob = d;
		fid = id;
	}

	void input(Scanner s) {
		counter();
		ticketno = "Ticket" + (c + 1);
		CID = c + 1;
		System.out.println("Enter FName");
		fname = s.next();
		System.out.println("Enter lName");
		lname = s.next();
		System.out.println("Enter religon");
		religon = s.next();
		System.out.println("Enter city");
		city = s.next();
		System.out.println("Enter nationality");
		nationality = s.next();
		System.out.println("Enter Email");
		Email = s.next();
		System.out.println("Enter phoneNo");
		phoneNo = s.next();
		System.out.println("Enter cnic");
		cnic = s.next();
		System.out.println("Enter Passport No ");
		pid = s.next();
		System.out.println("Enter Date of Birth yyyy mm dd");
		Dob = new Date(s.nextInt(), s.nextInt(), s.nextInt());

		insert();

	}

	public String toString() {
		String s = ticketno + "\t" + fid + "\t" + fname + "\t" + lname + "\t" + religon + "\t" + city + "\t"
				+ nationality + "\t" + Email + "\t" + phoneNo + "\t" + cnic + "\t" + pid + "\t" + Dob.getYear() + "-"
				+ Dob.getMonth() + "-" + Dob.getDate();
		return s;
	}

	void filing() {
		String path = "C:\\Users\\Haseeb's PC\\Desktop\\Project files\\customer.txt";
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

	void assignflight(String ff) {
		int n = 0;
		fid = ff;
		try {
			DBhandler db = new DBhandler();
			ResultSet result = db.getdata("select * from CUSTOMER");

			while (result.next()) {
				n = result.getInt("cid");
				if (CID == n) {
					String s = "update customer set Fid= '" + ff + "' where cid =" + (n);
					db.change(s);
				}
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}

	void counter() {
		String no = "";
		try {
			DBhandler db = new DBhandler();
			ResultSet result = db.getdata("select * from customer");
			String n = null;
			while (result.next()) {

				n = result.getString("ticketno");
				no = n.substring(6);
			}
			int number = 0;
			if (n != null) {
				number = Integer.parseInt(no);
				c = number;
			}

		} catch (SQLException e) {
		}
	}

	void insert() {
		try {
			DBhandler db = new DBhandler();

			String s = "insert into customer (TicketNo,Fname,Lname,PassportNumber,religion,city,nationality,Email,PhoneNo,Cnic,cid,DOB) values ('"
					+ ticketno + "','" + fname + "','" + lname + "','" + pid + "','" + religon + "','" + city + "','"
					+ nationality + "','" + Email + "','" + phoneNo + "','" + cnic + "'," + CID + ",'" + Dob.getYear()
					+ "-" + Dob.getMonth() + "-" + Dob.getDate() + "');";
			db.change(s);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void display() {
		System.out.println(fname + "\t" + lname + "\t" + pid + "\t" + religon + "\t" + city + "\t" + nationality + "\t"
				+ Email + "\t" + phoneNo + "\t" + cnic + "\t" + Dob.getYear() + "-" + Dob.getMonth() + "-"
				+ Dob.getDate());
	}

}
