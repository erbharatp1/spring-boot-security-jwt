package in.info.bharat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.info.bharat.model.Employee;

/**
 * Bharat patel
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}