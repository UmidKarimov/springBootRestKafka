package springkafkarest.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import springkafkarest.demo.entity.Employee;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Component
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.unwrap(Session.class);
        Query query = session.createQuery("from Employee", Employee.class);
        List<Employee> list = query.getResultList();
        return list;
    }

    @Override
    public void saveEmployee(Employee employee) {

        Session session = sessionFactory.unwrap(Session.class);
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.unwrap(Session.class);
        return session.get(Employee.class, id);


    }

    @Override
    public void deleteEmployee(int id) {

        Session session = sessionFactory.unwrap(Session.class);

        Query query = session.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
