package co.parameta.employees.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import co.parameta.employees.model.Employee;
import co.parameta.employees.repository.EmployeeRepository;

import co.parameta.springsoap.gen.SaveEmployeeRequest;
import co.parameta.springsoap.gen.SaveEmployeeResponse;

@Endpoint
public class EmployeeSOAPController {
	private static final String NAMESPACE_URI = "http://parameta.co/springsoap/gen";
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
    public EmployeeSOAPController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveEmployeeRequest")
    @ResponsePayload
    public SaveEmployeeResponse saveEmployee(@RequestPayload SaveEmployeeRequest request) throws ParseException {
		
		SaveEmployeeResponse response = new SaveEmployeeResponse();
		
        Employee emp = new Employee();
        emp.setFirstName(request.getFirstName());        
        emp.setLastName(request.getLastName());
        emp.setTypeDocument(request.getTypeDocument());
        emp.setDocument(request.getDocument());
        emp.setBithday(request.getBithday());
        emp.setLinkingDate(request.getLinkingDate());
        emp.setPosition(request.getPosition());
        emp.setSalary(request.getSalary());       
                
        Employee empSave = employeeRepository.save(emp);
        
        if(empSave.getId() > 0)
        {
        	co.parameta.springsoap.gen.Employee value = new co.parameta.springsoap.gen.Employee();
        	value.setId(empSave.getId());
        	value.setFirstName(empSave.getFirstName());
        	value.setLastName(empSave.getLastName());
        	value.setTypeDocument(empSave.getTypeDocument());
        	value.setDocument(empSave.getDocument());
        	value.setBithday(empSave.getBithday());
        	value.setLinkingDate(empSave.getLinkingDate());
        	value.setPosition(empSave.getPosition());
        	value.setSalary(empSave.getSalary());        	
        	
			response.setEmployee(value);
        	
        }
		
        return response;
    }
	

}
