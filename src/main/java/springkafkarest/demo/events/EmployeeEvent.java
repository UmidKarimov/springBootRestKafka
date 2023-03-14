package springkafkarest.demo.events;

import springkafkarest.demo.entity.Employee;

public class EmployeeEvent {

    Employee employee;
    EmployeeEventTypes eventType;

    public EmployeeEvent(Employee employee, EmployeeEventTypes eventType) {
        this.employee = employee;
        this.eventType = eventType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeEventTypes getEventType() {
        return eventType;
    }

    public void setEventType(EmployeeEventTypes eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "EmployeeEvent{" +
                "employee=" + employee +
                ", eventType=" + eventType +
                '}';
    }
}
