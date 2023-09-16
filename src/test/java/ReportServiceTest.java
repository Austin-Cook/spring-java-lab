import dao.ClassDAO;
import model.Class;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.report.ReportService;
import service.report.ScheduleReportService;
import service.response.StudentNewClass;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

//TODO: Add appropriate annotations to wire up this test using the context.xml file
public class ReportServiceTest {
    String correctOutput = "Printing new schedule: " + System.getProperty("line.separator") +
            "Report {studentID=12345, name='C. Brown', address='12 Apple St.', phone='555-1234'}" +
            System.getProperty("line.separator") +
            "     Taken Prerequisite: EE300" + System.getProperty("line.separator") +
            "     Next semester's classes:" + System.getProperty("line.separator") +
            "          Class{classID=10001, className='EE300', room='Room 0', professor='Dr. Junior'}" +
            System.getProperty("line.separator") +
            "Report {studentID=67890, name='L. Van Pelt', address='34 Pear Ave.', phone='555-5678'}" +
            System.getProperty("line.separator") +
            "     Taken Prerequisite: PH201" + System.getProperty("line.separator") +
            "     Next semester's classes:" + System.getProperty("line.separator") +
            "          Class{classID=10000, className='PH201', room='Room 1', professor='Dr. Evil'}"
            + System.getProperty("line.separator");

    ClassDAO classDAOMock;

    ReportService reportService;

    List<StudentNewClass> newClasses;

    List<Class> classList1;

    List<Class> classList2;

    @Test
    public void correctInitialization() {
        Assertions.assertNotNull(classDAOMock, "classDAOMock not wired");
        Assertions.assertNotNull(reportService, "reportService not wired");

        ScheduleReportService scheduleService = (ScheduleReportService) reportService;
        Assertions.assertEquals(classDAOMock, scheduleService.getClassDAO(), "classDAOMock was not injected");
        Assertions.assertNotNull(scheduleService.getClasses(), "List<Class> classes was not injected in ScheduleReportService");
    }
    @Test
    public void correctNewClassesList() {
        List<StudentNewClass> expectedNewClasses = new ArrayList<>();
        expectedNewClasses.add(new StudentNewClass(12345, "C. Brown", "12 Apple St.",
                "555-1234", "EE200", "C", "EE300"));
        expectedNewClasses.add(new StudentNewClass(67890, "L. Van Pelt", "34 Pear Ave.",
                "555-5678","PH100","C+","PH201"));

        Assertions.assertEquals(expectedNewClasses, newClasses, "newClasses not wired properly");
    }

    @Test
    public void correctClassList1() {
        List<Class> expectedClassList = new ArrayList<>();
        expectedClassList.add(new Class(10001, "EE300", "Room 0", "Dr. Junior"));
        Assertions.assertEquals(expectedClassList, classList1, "classList1 not wired properly");
    }

    @Test
    public void correctClassList2() {
        List<Class> expectedClassList = new ArrayList<>();
        expectedClassList.add(new Class(10000, "PH201", "Room 1", "Dr. Evil"));
        Assertions.assertEquals(expectedClassList, classList2, "classList2 not wired properly");
    }

    @Test
    public void scheduleReportServicePrint() throws Exception {
        ByteArrayOutputStream savedOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(savedOutput);

        // TODO: Insert Mockito.when() code here:

        reportService.printReport(ps);
        Assertions.assertEquals(correctOutput, savedOutput.toString());
        System.out.println(savedOutput.toString());
    }
}
