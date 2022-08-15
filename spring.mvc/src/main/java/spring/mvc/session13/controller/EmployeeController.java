package spring.mvc.session13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.mvc.session13.entity.Employee;
import spring.mvc.session13.repository.EmployeeDao;
import spring.mvc.session13.repository.NowDao;

@RestController
@RequestMapping("/jdbc/employee")
public class EmployeeController {
	
	@Autowired 
	private  EmployeeDao employeeDao;
	
	
	@GetMapping("/query/json")
	public List<Employee> queryJson() {
		return employeeDao.query();
	}

}
