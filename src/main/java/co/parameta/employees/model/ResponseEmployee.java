package co.parameta.employees.model;

public class ResponseEmployee {
	
	private Employee employee;
	
	private String message;
	
	private String timeofLinkingCompany;
	
	private String currentEmployeeAge;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimeofLinkingCompany() {
		return timeofLinkingCompany;
	}

	public void setTimeofLinkingCompany(String timeofLinkingCompany) {
		this.timeofLinkingCompany = timeofLinkingCompany;
	}

	public String getCurrentEmployeeAge() {
		return currentEmployeeAge;
	}

	public void setCurrentEmployeeAge(String currentEmployeeAge) {
		this.currentEmployeeAge = currentEmployeeAge;
	}
	
}
