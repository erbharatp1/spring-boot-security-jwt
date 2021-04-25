package in.info.bharat.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.info.bharat.model.Employee;
import in.info.bharat.service.EmployeeService;

/**
 * Bharat patel
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService service;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		logger.info("EmployeeController.firstPage()");
		return "Hello World";
	}

	// 1. save one Employee
	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		logger.info("save ", employee);
		ResponseEntity<String> resp = null;
		try {
			// if Employee Id exist
			if (employee.getEmpId() != null && service.isEmployeeExist(employee.getEmpId())) {
				resp = new ResponseEntity<String>("Given Id '" + employee.getEmpId() + "' Data already exist",
						HttpStatus.BAD_REQUEST);

			} else { // Employee id not exist
				Integer id = service.saveEmployee(employee);
				resp = new ResponseEntity<String>("Employee '" + id + "' created Successfully!", HttpStatus.OK // 200
				// HttpStatus.CREATED //201
				);
			}

		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Save Employee", HttpStatus.INTERNAL_SERVER_ERROR // Our App Got
																											// Exception
			);
			e.printStackTrace();
		}
		return resp;
	}

	// 2. Get One Employee by Id
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneEmployee(@PathVariable Integer id) {
		logger.info("getOneEmployee ", id);
		ResponseEntity<?> resp = null;
		try {
			// communicate with DB using ID with Serivce Layer
			Optional<Employee> opt = service.getOneEmployee(id);

			if (opt.isPresent()) { // if Employee exist
				Employee employee = opt.get();
				resp = new ResponseEntity<Employee>(employee, HttpStatus.OK);

			} else { // if Employee not exist
				resp = new ResponseEntity<String>("Employee '" + id + "' Not exist!", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to fetch Employee", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}

	// 3. Get All Employees
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees() {
		logger.info("getOneEmployee ");
		ResponseEntity<?> resp = null;
		try {

			List<Employee> list = service.getAllEmployees();
			resp = new ResponseEntity<List<Employee>>(list, HttpStatus.OK);

		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to fetch Employees", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	// 4. update Employee
	@PutMapping("/modify")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
		logger.info("updateEmployee ", employee);
		ResponseEntity<String> resp = null;
		try {
			// if Employee exist - then update
			if (employee.getEmpId() != null && service.isEmployeeExist(employee.getEmpId())) {
				service.updateEmployee(employee);
				resp = new ResponseEntity<String>("Employee '" + employee.getEmpId() + "' Updated!", HttpStatus.OK);

			} else {
				// if Employee not exist - return error message
				resp = new ResponseEntity<String>("Employee '" + employee.getEmpId() + "' not exist!",
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to update Employees", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}

	// 5. Delete one Employee
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeEmployee(@PathVariable Integer id) {
		logger.info("removeEmployee ", id);
		ResponseEntity<String> resp = null;
		try {
			// if Employee exist based on Id - DELETE call
			if (service.isEmployeeExist(id)) {
				service.deleteEmployee(id);
				resp = new ResponseEntity<String>("Employee '" + id + "' Deleted!", HttpStatus.OK);
			} else {
				// if given Employee id not exist
				resp = new ResponseEntity<String>("Employee '" + id + "' not exist",
						// HttpStatus.NOT_FOUND
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Delete Employee", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}

}