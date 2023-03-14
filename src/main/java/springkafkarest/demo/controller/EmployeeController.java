package springkafkarest.demo.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import springkafkarest.demo.entity.Employee;
import springkafkarest.demo.events.EmployeeEvent;
import springkafkarest.demo.events.EmployeeEventTypes;
import springkafkarest.demo.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private Gson jsonConverter;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") int id){


        Employee employee = employeeService.getEmployeeById(id);


        return employee;
    }

    @PostMapping("/employees")
    public void addNewEmployee(@RequestBody Employee employee){

        EmployeeEvent event = new EmployeeEvent(employee, EmployeeEventTypes.ADD);
        kafkaTemplate.send("employee-events", jsonConverter.toJson(event));

    }

    @PutMapping("/employees")
    public void updateEmployee(@RequestBody Employee employee){

        EmployeeEvent event = new EmployeeEvent(employee, EmployeeEventTypes.PATCH);
        kafkaTemplate.send("employee-events", jsonConverter.toJson(event));
        //employeeService.saveEmployee(employee);



    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable("id") int id){

        Employee employee = employeeService.getEmployeeById(id);
        EmployeeEvent event = new EmployeeEvent(employee, EmployeeEventTypes.DELETE);
        kafkaTemplate.send("employee-events", jsonConverter.toJson(event));
        //employeeService.deleteEmployee(id);


    }



}
