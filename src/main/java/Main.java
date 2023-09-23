import dao.StudentDAO;
import model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import service.report.ReportService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        // The following code initializes this project using the Spring configuration in applicationContext.xml
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // Use the Java configuration file rather than the applicationContext.xml
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get the Spring bean that manages access to Database
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

        // New Code
        int studentID = 12345;
        StudentDAO studentDAO = context.getBean(StudentDAO.class);
        Student student = studentDAO.getStudentById(studentID);

        // Using the JdbcTemplate to query for a student in the database
//        Student student = jdbcTemplate.queryForObject("SELECT * FROM Students WHERE studentID=?", new Object[]{12345},
//                new BeanPropertyRowMapper<>(Student.class));
        System.out.println("Using queryForObject method from JdbcTemplate to query studentID=" + studentID + ": ");
        if (student != null) {
            System.out.println(student.toString());
        }

        // New code
        List allStudents = studentDAO.getAllStudents();

        // Using the JdbcTemplate to query for all students in the database
        System.out.println("\n" +
                "Using the query method from JdbcTemplate to query all students: ");
//        List allStudents = jdbcTemplate.query("SELECT * FROM Students",
//                new BeanPropertyRowMapper<>(Student.class));
        for (Object stud : allStudents) {
            System.out.println(stud.toString());
        }

//        System.out.println("\n-----Implementation of ReportService----- ");
//        ReportService reportService = (ReportService) context.getBean("reportService");
//        reportService.printReport(System.out);

    }
}
