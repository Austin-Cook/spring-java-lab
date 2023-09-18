package dao;

import model.Student;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.EmptyStackException;
import java.util.List;

@Component    // Tells Spring to create a bean (if you intend to have more than one instance of the class, modify the tag to be `@Component("BEAN_NAME")`)
public class StudentDAOImpl implements StudentDAO {
    private JdbcTemplate jdbcTemplate;

    public StudentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student getStudentById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Students WHERE studentID=?", new Object[]{12345},
                    new BeanPropertyRowMapper<>(Student.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return jdbcTemplate.query("SELECT * FROM Students",
                new BeanPropertyRowMapper<>(Student.class));
    }
}
