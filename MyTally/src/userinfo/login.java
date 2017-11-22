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

@WebServlet("/loginform")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");

		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/MyTally","root","root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select username,password from registration where username='"+username+"'");
		String u = new String();
		String p = new String();
		while(rs.next()){
			u = rs.getString("username");
			p  =rs.getString("password");
		}
		if(u.equals(username) && p.equals(password))
			response.sendRedirect("menu.jsp");
		else
			response.sendRedirect("index.jsp");
		st.close();
		con.close();
		}
		catch(Exception e){}
	}

}
