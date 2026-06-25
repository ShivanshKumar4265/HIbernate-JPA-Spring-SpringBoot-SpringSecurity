package backend.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import backend.jdbc_utils.DBConnection;
import backend.modals.Student;


public class StudentDAO {

    public ArrayList<Student> getAllStudents() {

        ArrayList<Student> students = new ArrayList<>();

        try (
                Connection connection = DBConnection.getConnection();

                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery("select * from student")
        ) {

            while (rs.next()) {

                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("mobile_number"),
                        rs.getString("email"),
                        rs.getString("address")
                );
                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    public Student insertStudent(Student student) {
        String insertStudentQuery = "insert into student(id, name, mobile_number, email, address) values(?,?,?,?,?);";
        System.out.println("student details in student dao in insert student : " + student.toString());
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertStudentQuery);
        ) {

            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getMobile_number());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setString(5, student.getAddress());

            int queryResult = preparedStatement.executeUpdate();
            String msg = queryResult == 0 ? "Faaaaaaaaaaaaaaaaaaaa........." : "7 X 7";
            System.out.println(msg);

        } catch (SQLException e) {
            System.out.println("insert student exception " + e.getMessage());
            return null;
        }
        return student;
    }

    public Student findByEmail(String email) {

        String searchEmailQuery = "select * from student where email = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(searchEmailQuery);
        ) {

            preparedStatement.setString(1, email);

            try (
                    ResultSet rs = preparedStatement.executeQuery();
            ) {

                System.out.println("email input by user : " + email);

                if (rs.next()) {

                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("mobile_number"),
                            rs.getString("email"),
                            rs.getString("address")
                    );

                } else {
                    return null;
                }

            }

        } catch (SQLException e) {

            System.out.println("find email exception: " + e.getMessage());

        }

        return null;
    }

    /**
     *
     * @param studentList
     * @return
     *
     * will implement batch processing, to achieve the method functionality
     * to insert  the multiple data in db at a time.
     */
    public List<Student> saveMultipleStudent(List<Student> studentList){

        return studentList;
    }
}
