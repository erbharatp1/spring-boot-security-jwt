package in.info.bharat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.info.bharat.model.Manager;

/**
 * Bharat patel
 */
@Repository
public interface UserRepository extends CrudRepository<Manager, Integer> {
	
	Manager findByUsername(String username);
	
}