package service.report;

import dao.ClassDAO;
import model.Class;
import service.response.StudentNewClass;

import java.io.PrintStream;
import java.util.List;

/**
 * Service class which outputs the student’s schedules for the next semester based on the classes
 * they’ve taken in the current semester. It takes in new Class objects, describing the classes of the next semester.
 */
public class ScheduleReportService implements ReportService {
    private PrintStream stream;
    private ClassDAO classDAO;
    private List<Class> classes;

    public ScheduleReportService(ClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    /**
     * The printReport() method checks what prerequisites were fulfilled by the classes students have taken in the
     * current semester. It then deletes the classes of the previous semester in the server, insert the classes of the
     * new semester, then outputs the classes each student should take in the new semester.
     * @param stream The type of stream being passed in.
     */
    @Override
    public void printReport(PrintStream stream) {
        System.out.println("ScheduleReportService invoked. ");
        if (classes == null) {
            System.out.println("No new classes were injected. Use setter injection to inject new classes for the semester.");
            return;
        }
        this.stream = stream;
        stream.println("Printing new schedule: ");
        List<StudentNewClass> newClasses = classDAO.getNewClasses();
        classDAO.deleteAllClasses();

        for (Class obj : classes) {
            classDAO.createAClass(obj);
        }

        for (int i = 0; i < newClasses.size(); i++) {
            stream.println(newClasses.get(i).toString());
            stream.println("     Taken Prerequisite: " + newClasses.get(i).getPrerequisiteFor());
            stream.println("     Next semester's classes:");
            printNewClass(newClasses.get(i));
            for (int j = i + 1; j < newClasses.size(); j++) {
                if (newClasses.get(j).getStudentID() == newClasses.get(i).getStudentID()) {
                    printNewClass(newClasses.get(j));
                    newClasses.remove(j);
                    j--;
                }
            }
        }
        // Restores the database to the way it was so you can repeat this function with the same result.
        classDAO.restoreDB();
    }

    private void printNewClass(StudentNewClass studentNewClass) {
        if (studentNewClass.getGrade().contains("F")) {
            return;
        }
        List<Class> query = classDAO.queryClassesByName(studentNewClass.getPrerequisiteFor());
        if (query.size() == 1) {
            stream.println("          " + query.get(0).toString());
        }
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public ClassDAO getClassDAO() {
        return classDAO;
    }
}
