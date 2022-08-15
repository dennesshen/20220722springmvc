package spring.mvc.session13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.mvc.session13.entity.Job;
import spring.mvc.session13.repository.JobDao;

@RestController
@RequestMapping("/jdbc/job")
public class JobController {
	
	@Autowired 
	private  JobDao jobDao;
	
	
	@GetMapping("/query/json")
	public List<Job> queryJson() {
		return jobDao.query();
	}

}
