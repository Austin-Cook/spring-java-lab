package dao;

import model.Class;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import service.response.StudentGrade;
import service.response.StudentNewClass;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class ClassDAOImpl implements ClassDAO {
    JdbcTemplate jdbcTemplate;

    public ClassDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Class> getAllClasses() {
        return jdbcTemplate.query("SELECT * FROM Classes", new BeanPropertyRowMapper<>(Class.class));
    }


    @Override
    public List<StudentNewClass> getNewClasses() {
        return jdbcTemplate.query("SELECT Students.studentID, Students.name, Students.address, " +
                        "Students.phone, csg.className, csg.grade, cp.className as prerequisiteFor " +
                        "FROM Students, csg, cp "+
                        "WHERE Students.studentID=csg.studentID AND csg.className=cp.prerequisite;",
                new BeanPropertyRowMapper<>(StudentNewClass.class));
    }

    @Override
    public List<StudentGrade>  getStudentGrades() {
        return jdbcTemplate.query("SELECT Students.studentID, Students.name, Students.address, " +
                        "Students.phone, csg.className, Classes.professor, csg.grade " +
                        "FROM Students, csg, Classes "+
                        "WHERE Students.studentID=csg.studentID AND Classes.className=csg.className;",
                new BeanPropertyRowMapper<>(StudentGrade.class));
    }

    @Override
    public List<Class> queryClassesByName(String className) {
        return jdbcTemplate.query("SELECT * FROM Classes WHERE className=?", new Object[]{className},
                new BeanPropertyRowMapper<>(Class.class));
    }

    @Override
    public boolean createAClass(Class aClass) {
        if (aClass == null) {
            return false;
        }
        jdbcTemplate.update("INSERT INTO Classes(classID, className, room, professor) values(?,?,?,?)",
                aClass.getClassID(), aClass.getClassName(), aClass.getRoom(), aClass.getProfessor());
        return true;
    }

    @Override
    public boolean deleteAllClasses() {
        jdbcTemplate.update("DELETE FROM Classes");
        return true;
    }

    @Override
    public void restoreDB() {
        Resource resource = new FileSystemResource(new File("./table_script.sql"));
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        databasePopulator.execute(Objects.requireNonNull(jdbcTemplate.getDataSource()));
    }
}
