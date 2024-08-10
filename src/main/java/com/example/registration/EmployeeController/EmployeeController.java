package com.example.registration.EmployeeController;


import com.example.registration.DTO.EmployeeDTO;
import com.example.registration.DTO.LoginDTO;
import com.example.registration.Service.EmployeeService;
import com.example.registration.payloadResponse.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

   

   

    @GetMapping(path = "/hello")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("hello");
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> loginEmployee(@RequestBody LoginDTO loginDTO ){

        System.out.println("saveEmployee >>>>>>>>>");
        
        LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);


        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        System.out.println("saveEmployee >>>>>>>>>");
        String id = employeeService.addEmployee(employeeDTO);
        return id;
    }
}
