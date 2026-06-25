package myjdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {


    StudentDao() {
    }


    public int insertStudent(StudentModel studentModel) {
        String query = "insert into students(name, email, grade) values(?,?,?)";
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, studentModel.getName());
            preparedStatement.setString(2, studentModel.getEmail());
            preparedStatement.setString(3, studentModel.getGrade());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("insert student error " + e.getMessage());
        }
        return 0;
    }

    public int deleteStudentById(int studentId) {
        String query = "delete from students where id = ?";
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setInt(1, studentId);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("delete student exception " + e.getMessage());
        }
        return 0;
    }

    public List<StudentModel> getStudentList() {
        String query = "select * from students";
        List<StudentModel> studentModels = new ArrayList<>();

        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                StudentModel studentModel = new StudentModel(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("grade")
                );
                studentModels.add(studentModel);
            }

            return studentModels;

        } catch (SQLException e) {
            System.out.println("student list exception " + e.getMessage());
        }
        return  studentModels;
    }


    public  int[] insertAllStudent(List<StudentModel> studentModelList){
        String query = "insert into students(name, email, grade) values(?,?,?)";
        try(
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){

            for(int i = 0;i< studentModelList.size();i++){
                preparedStatement.setString(1, studentModelList.get(i).getName());
                preparedStatement.setString(2,studentModelList.get(i).getEmail());
                preparedStatement.setString(3,studentModelList.get(i).getGrade());
                preparedStatement.addBatch();
            }


            return  preparedStatement.executeBatch();

        } catch (SQLException e) {
            System.out.println("insert all exceptio "+ e.getMessage());
        }

        return new int[0];
    }

}
