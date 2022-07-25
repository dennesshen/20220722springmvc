package spring.mvc.session08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class MVController {
	
	@RequestMapping("/welcome")
	@ResponseBody
	public String welcome() {

		return "Welcome SpringMVC";
	}
}
