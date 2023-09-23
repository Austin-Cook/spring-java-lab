import model.Student;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration  // Shows this is a config file
@ComponentScan(basePackages={"dao", "aspect"})  // Replaces <context:component-scan base-package="dao,aspect" /> in applicationContext.xml
@EnableAspectJAutoProxy // Replaces <aop:aspectj-autoproxy /> in applicationContext.xml
@EnableTransactionManagement // Replaces <tx:annotation-driven /> in applicationContext.xml
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        // The bean's class is its type declaration "DataSource"
        // It's id is the name "dataSource"

        // instantiation of the dataSource bean
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // configure the bean
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/studentdb");
        dataSource.setUsername("root");
        dataSource.setPassword("rKiQ4$&N#!MR^qN");

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set DataSource
        sessionFactory.setDataSource(dataSource());

        // set AnnotatedClasses
        sessionFactory.setAnnotatedClasses(Student.class);

        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");

        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//
//        transactionManager.setSessionFactory(sessionFactory.getObject());
//
//        return transactionManager;
        return new HibernateTransactionManager(sessionFactory);
    }

    // For the classes that aren't accessed with hibernate
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
