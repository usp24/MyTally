package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import my.myException;
import dao.userinfoDAO;
import vo.userinfoVO;

@WebServlet("/userinfo")
public class userinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ch = (String)request.getParameter("choice");
		switch(ch){
		
		/*case "registration" : registration(request,response);
							  break;*/
		case "login" : login(request,response);
					   break;
		default : System.out.println("*** DEFAULT CASE");
				  response.sendRedirect("index.jsp");
				  break;
		}
	}
	/*public void registration(HttpServletRequest request,HttpServletResponse response)throws IOException{
		
		String businessName = (String)request.getParameter("businessName");
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		String email = (String)request.getParameter("email");
		
		userinfoVO rvo = new userinfoVO();
		rvo.setBusinessName(businessName);
		rvo.setUsername(username);
		rvo.setPassword(password);
		rvo.setEmail(email);
		
		userinfoDAO rdao = new userinfoDAO();
		try{
			rdao.insert(rvo);
			response.sendRedirect("index.jsp");
		}
		catch(myException s){
			response.sendRedirect("index.jsp");
		}
		catch(Exception s){
			System.out.println("userinfo :: doPost :: registration :: "+s);
			response.sendRedirect("index.jsp");
		}
	}*/
	
	public void login(HttpServletRequest request,HttpServletResponse response)throws IOException{
		
		String lgusername = (String)request.getParameter("username");
		String lgpassword = (String)request.getParameter("password");
		
		userinfoVO rvo = new userinfoVO();
		rvo.setLgusername(lgusername);
		rvo.setLgpassword(lgpassword);
		
		userinfoDAO rdao = new userinfoDAO();
		try{
			rdao.verify(rvo);
			response.sendRedirect("menu.jsp");
		}
		catch(myException s){
			response.sendRedirect("index.jsp");
			HttpSession session = request.getSession();
			session.setAttribute("login_error", s.loginFail());
		}
		catch(Exception s){
			System.out.println("userinfo :: doPost :: login :: "+s);
			response.sendRedirect("index.jsp");
		}
	}
}
