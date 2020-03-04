package co.parameta.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.parameta.employees.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
