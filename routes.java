import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Scanner;

public class routes {

	String rid,src,dest;
	Flight f;
	Date d;
	Time t;
	static int c=0;
	routes(){}
	routes(String ri,String sr,String de,Date d,Time t){
		rid=ri;
		src=sr;
		dest=de;
		this.d=d;
		this.t=t;
	}
	void input(Scanner s)
	{
		counter();
		rid="R"+(++c);
		System.out.println("Enter Source of flight");
		src=s.next();
		System.out.println("Enter Destination of flight");
		dest=s.next();
		System.out.println("Enter Date format yyyy mm dd ");
		d=new Date(s.nextInt(),s.nextInt(),s.nextInt()); 
		System.out.println("Enter Time hh mm ss ");
		t=new Time(s.nextInt(),s.nextInt(),s.nextInt());
		 insert();
		filing();
		
	}
	void update(Scanner s,String r)
	{
		System.out.println("enter Flight Type");
		String ty=s.next();
		System.out.println("Enter Source of flight");
		src=s.next();
		System.out.println("Enter Destination of flight");
		dest=s.next();
		System.out.println("Enter Date format yyyy mm dd ");
		d=new Date(s.nextInt(),s.nextInt(),s.nextInt()); 
		System.out.println("Enter Time hh mm ss ");
		t=new Time(s.nextInt(),s.nextInt(),s.nextInt());
		String ss="update routes set source='"+src+"',destination='"+dest+"',date='"+d.getYear()+"-"+d.getMonth()+"-"+d.getDate()+"',time='"+t.getHours()+":"+t.getMinutes()+":"+t.getSeconds() +"'where Rid = '"+r+"'";
		DBhandler db = new DBhandler();
		db.change(ss);
		f=new Flight();
		
	}
	void counter()
	{
		String no="";
		try {
			DBhandler db = new DBhandler();
			ResultSet result = db.getdata("select Rid from Flight");
			String n=null;
		while(result.next()) {
		
			 n  = result.getString("Rid");
			 no = n.substring(1);
			}
			int number=0;
			if(n!=null)
			{
				number= Integer.parseInt(no);
				c=number;
			}
			}catch(SQLException e) {}
	}
	String getrid()
	{
		return rid;
	}
	void display()
	{
		
		System.out.println(rid+"\t"+src+"\t"+dest+"\t"+d+"\t"+t);
			
	}
	public String toString()
	{
		String s=rid+"\t"+src+"\t"+dest+"\t"+d.getYear()+"-"+d.getMonth()+"-"+d.getDate()+"\t"+t.getHours()+":"+t.getMinutes()+":"+t.getSeconds();
		return s;
	}
	void filing() {
		String path = "C:\\Users\\Haseeb's PC\\Desktop\\Project files\\Routes.txt";
		File file = new File(path);
		try {
			file.createNewFile();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			FileWriter fw = new FileWriter(path, true);
			fw.write(toString());
			fw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	void insert()
	{
		String q="insert into Routes(Rid,Source,Destination,Date,Time) values('"+rid+"','"+src+"','"+dest+"','"+d.getYear()+"-"+d.getMonth()+"-"+d.getDate()+"','"+t.getHours()+":"+t.getMinutes()+":"+t.getSeconds()+"');";
		DBhandler db = new DBhandler();
		db.change(q);
		
	}
	
}
