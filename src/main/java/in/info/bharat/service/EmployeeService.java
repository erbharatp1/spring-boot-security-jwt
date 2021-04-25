package in.info.bharat.service;

import java.util.List;
import java.util.Optional;

import in.info.bharat.model.Employee;

/**
 * Bharat patel
 */
public interface EmployeeService {

	public Integer saveEmployee(Employee p);

	public void updateEmployee(Employee p);

	public void deleteEmployee(Integer id);

	public Optional<Employee> getOneEmployee(Integer id);

	public List<Employee> getAllEmployees();

	public boolean isEmployeeExist(Integer id);

}