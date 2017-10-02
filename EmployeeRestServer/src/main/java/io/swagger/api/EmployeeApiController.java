package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Employee;
import java.util.List;

import io.swagger.annotations.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java. util. Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;

import javax.validation.constraints.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-30T08:28:48.035Z")

@Controller
public class EmployeeApiController implements EmployeeApi {

 @Autowired
  private Geode cache;

    public ResponseEntity<Void> addEmployee(@ApiParam(value = "Employee objects that needs to be added" ,required=true )  @Valid @RequestBody List<Employee> body) {
        // do some magic!
        try
        {
            for (Employee e:body)
            {
                Long id = e.getId();

                //check whetger this id exists
                if (null != cache.getRegion().get(id.toString()) || false == e.checkIfValid())
                {
                    return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
                }
                
                //parse employee inthe json

                ObjectMapper objectMapper = new ObjectMapper();
                String value = objectMapper.writeValueAsString(e);
                System.out.println(value);

                //put into IMDG
                cache.getRegion().put(id.toString(), value);
                
            }
        }
        catch(IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteEmployee(@ApiParam(value = "employee objects that needs to be deleted: changed status to inactive" ,required=true )  @Valid @RequestBody List<Employee> body) {
        // do some magic!
        try {
            
            for (Employee e:body) {
                
                Long id = e.getId();
                
                System.out.println("deleteEmployee: id = " + id);

                //check whether this id exists
                String old_value = cache.getRegion().get(id.toString());
                
                if (null == old_value) {
                    System.out.println("deleteEmployee: id " + id + " not found");
                    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
                }             
                
                System.out.println("deleteEmployee: employee before merge " + old_value);
                
                ObjectMapper objectMapper = new ObjectMapper();
                
                Employee old_employee = objectMapper.readValue(old_value, Employee.class);

                //update orig employee info
                old_employee.setStatus(0);
                
                
                String value = objectMapper.writeValueAsString(old_employee);
                
                System.out.println("deleteEmployee: employee after merge " + value);
                //put into IMDG
                cache.getRegion().put(id.toString(), value);
                
            }
        }
        catch(IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Employee>> getAllEmployees() {
        // do some magic!
        Collection<String> values = cache.getRegion().values();
        
        List<Employee> list = new ArrayList<Employee>();
        for(String value:values){
            ObjectMapper objectMapper = new ObjectMapper();
                
            Employee employee;
            try {
                employee = objectMapper.readValue(value, Employee.class);
                
                //if active
                if (employee!=null && 1 == employee.getStatus())
                    list.add(employee);
                
            } catch (IOException ex) {
                Logger.getLogger(EmployeeApiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }

    public ResponseEntity<Employee> getEmployeeById(@ApiParam(value = "ID of employee to return",required=true ) @PathVariable("employeeId") Long employeeId) {
        // do some magic!
        String value = cache.getRegion().get(employeeId.toString());
        
        if (null == value)
        {
            System.out.println("updateEmployee: id " + employeeId + " not found");
            return new ResponseEntity<Employee>(new Employee(),HttpStatus.NOT_FOUND);
        }      
        ObjectMapper objectMapper = new ObjectMapper();
                
        Employee employee;
        try {
            employee = objectMapper.readValue(value, Employee.class);
        } catch (IOException ex) {
            Logger.getLogger(EmployeeApiController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<Employee>(new Employee(), HttpStatus.NOT_FOUND);
        }

        // check if active
        if (0 == employee.getStatus()) {
            return new ResponseEntity<Employee>(new Employee(), HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<Employee>(employee,HttpStatus.OK);
    }

    public ResponseEntity<Void> updateEmployee(@ApiParam(value = "employee objects that needs to be updated" ,required=true )  @Valid @RequestBody List<Employee> body) {
        // do some magic!
        try {
            
            for (Employee e:body) {
                
                Long id = e.getId();
                
                System.out.println("updateEmployee: id = " + id);

                //check whether this id exists
                String old_value = cache.getRegion().get(id.toString());
                
                if (null == old_value) {
                    System.out.println("updateEmployee: id " + id + " not found");
                    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
                }             
                
                System.out.println("updateEmployee: employee before merge " + old_value);
                
                ObjectMapper objectMapper = new ObjectMapper();
                
                Employee old_employee = objectMapper.readValue(old_value, Employee.class);

                //update orig employee info
                old_employee.merge(e);
                
                
                String value = objectMapper.writeValueAsString(old_employee);
                
                System.out.println("updateEmployee: employee after merge " + value);
                //put into IMDG
                cache.getRegion().put(id.toString(), value);
                
            }
        }
        catch(IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}

