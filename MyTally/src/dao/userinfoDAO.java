package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import my.myException;
import vo.userinfoVO;

public class userinfoDAO {
	
	Connection con;
	Statement st;
	
	/*public void insert(userinfoVO rvo)throws Exception,myException{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from userinfo where username='"+rvo.getUsername()+"'");
			if(rs.next()==false)
				st.executeUpdate("insert into userinfo(businessName,username,password,email) values('"+rvo.getBusinessName()+"','"+rvo.getUsername()+"','"+rvo.getPassword()+"','"+rvo.getEmail()+"')");
			else
				throw new myException();
		}
		catch(myException e){
			System.out.println(e.usernameInValid());
			throw e;
		}
		catch(Exception ob){
			System.out.println("userinfoDAO :: insert :: "+ob);
			throw ob;
		}
		finally{
			//st.close();
			//con.close();
		}
	}*/
	
	public void verify(userinfoVO rvo)throws Exception,myException{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from userinfo where username='"+rvo.getLgusername()+"' and password='"+rvo.getLgpassword()+"'");
			if(rs.next()==false)
				throw new myException();
		}
		catch(myException e){
			System.out.println(e.loginFail());
			throw e;
		}
		catch(Exception ob){
			System.out.println("userinfoDAO :: verify :: "+ob);
			throw ob;
		}
		finally{
			st.close();
			con.close();
		}
	}
}
