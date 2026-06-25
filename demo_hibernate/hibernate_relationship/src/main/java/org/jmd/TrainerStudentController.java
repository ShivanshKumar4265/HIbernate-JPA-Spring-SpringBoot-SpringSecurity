package org.jmd;

import org.jmd.dao.TrainerStudentDao;
import org.jmd.model.Student;

import java.util.ArrayList;
import java.util.List;

public class TrainerStudentController {
    public static void main(String[] args) {
        TrainerStudentDao trainerStudentDao = new TrainerStudentDao();
        List<Integer> trainer = new ArrayList<>(List.of(1,2));

        Student student1 = new Student();
        student1.setName("Nobita");

        Student student2  = new Student();
        student2.setName("Sizuka");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);

        trainerStudentDao.addTrainersAndStudent(trainer,studentList );

    }
}
