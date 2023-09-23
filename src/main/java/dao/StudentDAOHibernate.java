package dao;

import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class StudentDAOHibernate implements StudentDAO {
    private SessionFactory sessionFactory;

    public StudentDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly=true)
    public Student getStudentById(int id) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        Student student = session.get(Student.class, id);
//
//        transaction.commit();
//        session.close();
//        return student;

        // Here, we don't have to manage the transaction, because we defined the transactionManager bean
        return sessionFactory.getCurrentSession()
                .get(Student.class, id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Student> getAllStudents() {
        return sessionFactory.getCurrentSession()
                .createQuery("select a from Student a", Student.class)
                .getResultList();
    }
}
