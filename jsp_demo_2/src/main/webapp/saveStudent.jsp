<%@ page import="java.sql.*" %>

<%

String name = request.getParameter("name");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
String dob = request.getParameter("dob");
String address = request.getParameter("address");
String college = request.getParameter("college");
String batch = request.getParameter("batch");
String passoutYear = request.getParameter("passoutYear");
String parentPhone = request.getParameter("parentPhone");

Connection con = null;
PreparedStatement ps = null;

try{

    Class.forName("com.mysql.cj.jdbc.Driver");

    con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studentdb",
        "root",
        "password"
    );

    String query = "insert into students(name,email,phone,dob,address,college,batch,passout_year,parent_phone) values(?,?,?,?,?,?,?,?,?)";

    ps = con.prepareStatement(query);

    ps.setString(1, name);
    ps.setString(2, email);
    ps.setString(3, phone);
    ps.setString(4, dob);
    ps.setString(5, address);
    ps.setString(6, college);
    ps.setString(7, batch);
    ps.setString(8, passoutYear);
    ps.setString(9, parentPhone);

    int i = ps.executeUpdate();

    if(i > 0){
        out.println("<h2>Student Registered Successfully</h2>");
    }
    else{
        out.println("<h2>Registration Failed</h2>");
    }

}
catch(Exception e){
    out.println(e);
}

finally{

    if(ps != null)
        ps.close();

    if(con != null)
        con.close();
}

%>