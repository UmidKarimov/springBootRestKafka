package springkafkarest.demo.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import springkafkarest.demo.entity.Employee;
import springkafkarest.demo.events.EmployeeEvent;
import springkafkarest.demo.events.EmployeeEventTypes;
import springkafkarest.demo.service.EmployeeService;

@Component
public class KafkaListeners {


    @Autowired
    EmployeeService employeeService;
    @Autowired
    private Gson jsonConverter;

    @KafkaListener(topics = "employee-events")
    public void getFromKafka(String employee){
        System.out.println(employee);
        EmployeeEvent event = jsonConverter.fromJson(employee, EmployeeEvent.class);
        EmployeeEventTypes eventTypes = event.getEventType();
        Employee employeeInfo = event.getEmployee();
        if (eventTypes == EmployeeEventTypes.ADD || eventTypes == EmployeeEventTypes.PATCH)
        employeeService.saveEmployee(employeeInfo);
        else if (eventTypes == EmployeeEventTypes.DELETE)
        employeeService.deleteEmployee(employeeInfo.getId());
        else
            throw new NullPointerException("Not found event type");
    }
}
