package co.parameta.employees.controller;

import co.parameta.employees.model.Employee;
import co.parameta.employees.model.ResponseEmployee;
import co.parameta.employees.repository.EmployeeRepository;
import co.parameta.springsoap.gen.SaveEmployeeRequest;
import co.parameta.springsoap.gen.SaveEmployeeResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public ResponseEmployee getEmployee(@RequestParam String firstName,
    									@RequestParam String lastName,
    									@RequestParam String typeDocument,
    									@RequestParam String document,
    									@RequestParam @DateTimeFormat(pattern="dd-MM-yyyy") Date bithday,
    									@RequestParam @DateTimeFormat(pattern="dd-MM-yyyy") Date linkingDate,
    									@RequestParam String position,
    									@RequestParam Double salary) throws ParseException {
    	
    	ResponseEmployee res = new ResponseEmployee();
    	
    	if(calculateAge(bithday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()) >= 18)
    	{
    		EmployeeSOAPController controller = new EmployeeSOAPController(employeeRepository);
        	
        	SaveEmployeeRequest request = new SaveEmployeeRequest();
        	request.setFirstName(firstName);
        	request.setLastName(lastName);
        	request.setTypeDocument(typeDocument);
        	request.setDocument(document);
        	request.setBithday(bithday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
        	request.setLinkingDate(linkingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
        	request.setPosition(position);
        	request.setSalary(salary);
        	
    		SaveEmployeeResponse saveResponse = new SaveEmployeeResponse();
    	    saveResponse = controller.saveEmployee(request);        	
    		
    	    if(saveResponse.getEmployee() != null)
    	    {
    	    	res.setMessage("Employee Save!");
    	    	Employee employee = new Employee();
    	    	employee.setId(saveResponse.getEmployee().getId());
    	    	employee.setFirstName(saveResponse.getEmployee().getFirstName());
    	    	employee.setLastName(saveResponse.getEmployee().getLastName());
    	    	employee.setTypeDocument(saveResponse.getEmployee().getTypeDocument());
    	    	employee.setDocument(saveResponse.getEmployee().getDocument());
    	    	employee.setBithday(saveResponse.getEmployee().getBithday());
    	    	employee.setLinkingDate(saveResponse.getEmployee().getLinkingDate());
    	    	employee.setPosition(saveResponse.getEmployee().getPosition());
    	    	employee.setSalary(saveResponse.getEmployee().getSalary());
    	    	
				res.setEmployee(employee);
				res.setCurrentEmployeeAge(calculateYearsMonthsDays(bithday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()));
				res.setTimeofLinkingCompany(calculateYearsMonthsDays(linkingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()));
    	    }
    	}
    	else
    	{
    		res.setMessage("the employee must be of legal age");
    		
    	}
    	
    	
        return res;
    }
    
    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
    		   
    	return Period.between(birthDate, currentDate).getYears();
    }
    
    public String calculateYearsMonthsDays(LocalDate startDate, LocalDate currentDate){
    
    	Period p = Period.between(startDate , currentDate ) ;
    	int years = p.getYears();
    	int months = p.getMonths();
    	int days = p.getDays();
    	
    	return "Years:" + years + ",Months:" + months +",Days:" + days;
    }
}
