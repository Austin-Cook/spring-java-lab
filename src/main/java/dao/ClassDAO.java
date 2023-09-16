package dao;

import model.Class;
import service.response.StudentGrade;
import service.response.StudentNewClass;

import java.util.List;

public interface ClassDAO {

    List<Class> getAllClasses();

    List<StudentNewClass> getNewClasses();

    List<StudentGrade>  getStudentGrades();

    List<Class> queryClassesByName(String className);

    boolean createAClass(Class aClass);

    boolean deleteAllClasses();

    void restoreDB();
}
