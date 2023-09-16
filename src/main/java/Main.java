import model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // The following code initializes this project using the Spring configuration in applicationContext.xml
        // TODO Check src/main/resources/applicationContext.xml to see how it initialized the JdbcTemplate bean.
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get the Spring bean that manages access to Database
        // TODO Change the following line of code after you finish implementing StudentDAOImpl
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

        // Using the JdbcTemplate to query for a student in the database
        Student student = jdbcTemplate.queryForObject("SELECT * FROM Students WHERE studentID=?", new Object[]{12345},
                new BeanPropertyRowMapper<>(Student.class));
        System.out.println("Using queryForObject method from JdbcTemplate to query studentID=12345: ");
        if (student != null) {
            System.out.println(student.toString());
        }

        // Using the JdbcTemplate to query for all students in the database
        System.out.println("\n" +
                "Using the query method from JdbcTemplate to query all students: ");
        List allStudents = jdbcTemplate.query("SELECT * FROM Students",
                new BeanPropertyRowMapper<>(Student.class));
        for (Object stud : allStudents) {
            System.out.println(stud.toString());
        }

        System.out.println("\n-----Implementation of ReportService----- ");


    }
}
