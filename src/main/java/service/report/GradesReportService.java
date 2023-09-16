package service.report;

import dao.ClassDAO;
import service.response.StudentGrade;

import java.io.PrintStream;
import java.util.List;

/**
 * This class prints a report of the grades of the students of the current semester
 * using the implemented method printReport().
 */
public class GradesReportService implements ReportService {
    private ClassDAO classDAO;

    public GradesReportService(ClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    @Override
    public void printReport(PrintStream stream) {
        System.out.println("GradesReportService invoked. ");
        stream.println("Printing Student Grades: ");
        List<StudentGrade> grades = classDAO.getStudentGrades();

        // Just printing the query prettier. Could also chose to store the information in better model classes but
        // that requires custom row mapping.
        for(int i = 0; i < grades.size(); i++) {
            stream.println(grades.get(i).toString());
            stream.println(grades.get(i).gradeToString());
            for (int j = i+1; j< grades.size(); j++) {
                if (grades.get(j).getStudentID() == grades.get(i).getStudentID()) {
                    stream.println(grades.get(j).gradeToString());
                    grades.remove(j);
                    j--;
                }
            }
        }
    }
}
