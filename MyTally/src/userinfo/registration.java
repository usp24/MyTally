package userinfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrationfrom")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String name = (String)request.getParameter("businessName");
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		String email = (String)request.getParameter("email");
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/MyTally","root","root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select username from registration where username='"+username+"'");
			rs.next();
			String s = rs.getString("username");
			if(s==null){
				st.executeUpdate("insert into registration(businessName,username,password,email) values('name','"+username+"','"+password+"','"+email+"')");
				response.sendRedirect("index.jsp");
			}
			else{
				System.out.println("ERROR :: Choose another username");
				response.sendRedirect("registration.jsp");
			}
			st.close();
			con.close();
		}
		catch(Exception e){
			System.out.println("Exception");
		}
	}

}
