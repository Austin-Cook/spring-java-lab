package dao;

import model.Student;
import java.util.List;

/**
 * StudentDAO interface. You may initially found this to be redundant since this Spring project is a small
 * example of an actual server. However, it becomes useful when you want different DAO implementations for
 * testing purposes and for different databases this project may access.
 */
public interface StudentDAO {
    Student getStudentById(int id);

    List<Student> getAllStudents();
}
