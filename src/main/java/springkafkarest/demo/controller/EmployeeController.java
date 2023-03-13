package springkafkarest.demo.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import springkafkarest.demo.entity.Employee;
import springkafkarest.demo.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private KafkaTemplate kafkaTemplate;
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

        kafkaTemplate.send("add-employee", jsonConverter.toJson(employee));

    }

    @PutMapping("/employees")
    public void updateEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);



    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable("id") int id){
        Employee employee = employeeService.getEmployeeById(id);


        employeeService.deleteEmployee(id);


    }



}
