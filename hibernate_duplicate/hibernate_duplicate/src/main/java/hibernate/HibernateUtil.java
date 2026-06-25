package hibernate;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings
                Properties settings = new Properties();
                settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernateDemo?useSSL=false");
                settings.put("hibernate.connection.username", "root");
                settings.put("hibernate.connection.password", "Gate@202");
                settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                settings.put("hibernate.show_sql", "true");
                settings.put("hibernate.hbm2ddl.auto", "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Student.class);

                StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
                     .applySettings(configuration.getProperties());

                sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
