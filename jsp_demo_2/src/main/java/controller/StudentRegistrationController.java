package controller;

import java.io.IOException;
import java.io.PrintWriter;

import backend.DAO.StudentDAO;
import backend.modals.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/register")
public class StudentRegistrationController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I am in student registration controller");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String college = request.getParameter("college");
		String batch = request.getParameter("batch");
		String passoutYear = request.getParameter("passoutYear");
		String parentPhone = request.getParameter("parentPhone");
		
		// Generate unique ID from combination of email, phone, and dob
		String uniqueCombination = email + phone + dob;
		int uniqueId = Math.abs(uniqueCombination.hashCode());
		
		System.out.print(email + " " + name +  " " + phone + " " + dob + " " + address + " " + college + " " + batch + " " + passoutYear + " " + parentPhone);	
		
		StudentDAO studentDAO = new StudentDAO();
		Student student = studentDAO.insertStudent(new Student(uniqueId, name, phone, email, address));
		
		/**\
		 * After successful registration, you can redirect the user to a success page or display a success messag
		 */
		
		/*
		 * to redirect to a success page, you can use either of the following approaches:
		 * req.getRequestDispatcher("success.jsp").forward(req, resp);
		 * or
		 * resp.sendRedirect("success.jsp");
		 * 
		 * */
		
		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();
		
		if(student == null) {
			out.write(
				"<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"<meta charset='UTF-8'>" +
				"<title>Registration Failed</title>" +
				"<style>" +
				"body { font-family: Arial; background: #f2f2f2; }" +
				".container { width: 400px; margin: 100px auto; background: white; padding: 40px; border-radius: 8px; text-align: center; }" +
				".error { color: #dc3545; font-size: 24px; margin-bottom: 20px; }" +
				"p { font-size: 18px; color: #333; }" +
				"a { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #007bff; color: white; text-decoration: none; border-radius: 4px; }" +
				"</style>" +
				"</head>" +
				"<body>" +
				"<div class='container'>" +
				"<div class='error'>✗ Registration Failed</div>" +
				"<p>Sorry, there was an issue with your registration. Please try again.</p>" +
				"<a href='register.jsp'>Back to Home</a>" +
				"</div>" +
				"</body>" +
				"</html>"
			);
		}else {
			out.write(
					"<!DOCTYPE html>" +
					"<html>" +	
					"<head>" +
					"<meta charset='UTF-8'>" +
					"<title>Registration Success</title>" +
					"<style>" +
					"body { font-family: Arial; background: #f2f2f2; }" +
					".container { width: 400px; margin: 100px auto; background: white; padding: 40px; border-radius: 8px; text-align: center; }" +
					".success { color: #28a745; font-size: 24px; margin-bottom: 20px; }" +
					"p { font-size: 18px; color: #333; }" +
					"a { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #007bff; color: white; text-decoration: none; border-radius: 4px; }" +
					"</style>" +
					"</head>" +
					"<body>" +
					"<div class='container'>" +
					"<div class='success'>✓ Registration Successful</div>" +
					"<p>Welcome, <strong>" + name + "</strong>!</p>" +
					"<p>Your registration has been completed successfully.</p>" +
					"<a href='register.jsp'>Back to Home</a>" +
					"</div>" +
					"</body>" +
					"</html>"
				);
		}		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I am in student registration controller post method");
	}
}
		