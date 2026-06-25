package controller;

import java.io.IOException;

import backend.DAO.StudentDAO;
import backend.modals.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(value = "/login")
public class LoginController extends HttpServlet{
	
	@Overridec
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentDAO studentDAO = new StudentDAO();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Student student = studentDAO.findByEmail(email);
		if(student != null ) {
			System.out.println("Student found: " + student.toString());
			resp.sendRedirect("dashboard.jsp");
					
		} else {
			resp.sendRedirect("login.jsp?error=Invalid email or password");
		}
		
	}

}
