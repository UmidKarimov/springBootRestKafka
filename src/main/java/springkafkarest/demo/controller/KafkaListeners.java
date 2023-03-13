package springkafkarest.demo.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import springkafkarest.demo.entity.Employee;
import springkafkarest.demo.service.EmployeeService;

@Component
public class KafkaListeners {


    @Autowired
    EmployeeService employeeService;
    @Autowired
    private Gson jsonConverter;

    @KafkaListener(topics = "add-employee")
    public void getFromKafka(String employee){
        System.out.println(employee);
        employeeService.saveEmployee(jsonConverter.fromJson(employee, Employee.class));
    }
}
