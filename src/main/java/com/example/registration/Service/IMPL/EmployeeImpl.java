package com.example.registration.Service.IMPL;

import com.example.registration.DTO.EmployeeDTO;
import com.example.registration.DTO.LoginDTO;
import com.example.registration.Entity.Employee;
import com.example.registration.Repo.EmployeeRepo;
import com.example.registration.Service.EmployeeService;
import com.example.registration.payloadResponse.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
   private EmployeeRepo employeeRepo;


   @Override
   public LoginResponse loginEmployee(LoginDTO loginDTO) {
       Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());


       if(employee1==null) return new LoginResponse("Email does not exist", false);


         String payloadPassword = loginDTO.getPassword();
         String encodedPassword = employee1.getPassword();
         Boolean isPwdRight = passwordEncoder.matches(payloadPassword, encodedPassword);

         if(!isPwdRight) return new LoginResponse("Password does not exit", false);
       Optional<Employee> employee2 = employeeRepo.findOneByEmailAndPassword(employee1.getEmail(), encodedPassword);
       if(employee2.isPresent()) return new LoginResponse("Login success", true);
       return new LoginResponse("login failed", false);
   }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        System.out.println("addEmployee>>>>>>>>>>>, employeeDTO.getPassword()"+ employeeDTO.getPassword());
        Employee employee = new Employee( employeeDTO.getEmployeeId() ,employeeDTO.getEmployeeName() , employeeDTO.getEmail() , this.passwordEncoder.encode(employeeDTO.getPassword()));
        employeeRepo.save(employee);
         return employee.getEmployeeName();
    }

  
}
