package in.info.bharat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.info.bharat.model.Employee;
import in.info.bharat.repository.EmployeeRepository;

/**
 * Bharat patel
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository repo;

	@Override
	public Integer saveEmployee(Employee p) {
		return repo.save(p).getEmpId();
	}

	@Override
	public void updateEmployee(Employee p) {
		repo.save(p);
	}

	@Override
	public void deleteEmployee(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<Employee> getOneEmployee(Integer id) {
		return repo.findById(id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

	@Override
	public boolean isEmployeeExist(Integer id) {
		return repo.existsById(id);
	}

}