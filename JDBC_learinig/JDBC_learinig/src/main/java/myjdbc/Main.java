package myjdbc;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        StudentModel studentModel = new StudentModel("Shivansh kumar",  "Shiv@gamil.com", "A");
//        if(studentDao.insertStudent(studentModel) > 0){
//            System.out.println("Data inserted");
//        }else{
//            System.out.println("Something went wrong");
//        }

//        System.out.println(studentDao.deleteStudentById(2) > 0? "Date delete succesfull":"somethind went wrong try again");

//        System.out.println(studentDao.getStudentList().size() > 0 ? studentDao.getStudentList() : "There is no student present, please insert first");



        List<StudentModel> students = new ArrayList<>();

        for (int i = 200; i <= 500; i++) {
            students.add(
                    new StudentModel(
                            "Student" + i,
                            "student" + i + "@gmail.com",
                            (i % 3 == 0) ? "A" : (i % 3 == 1) ? "B" : "C"
                    )
            );
        }

        System.out.println(studentDao.insertAllStudent(students).length == 0 ?"something went wrong please try again" : "your student list inserted");
    }
}
