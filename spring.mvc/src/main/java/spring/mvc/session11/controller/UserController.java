package spring.mvc.session11.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session11.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	private List<User> users = new ArrayList<>();
	
	{
		users.add(new User("A01", 18, new Date(), "大學", "男", new String[] {"看電影", "飛控"}, "aaa"));
		users.add(new User("B02", 19, new Date(), "研究所", "女", new String[] {"爬山"}, "bbb"));
		users.add(new User("C03", 20, new Date(), "國中", "男", new String[] {"看電影", "寫程式"}, "ccc"));
	}
	
	@GetMapping("/")
	public String index(Model model,@ModelAttribute User user){
		
		model.addAttribute("users", users);
		model.addAttribute("_method", "POST");
		model.addAttribute("submitButtonName", ""
				+ "新增");
		
		return "session11/user";
	}
	@PostMapping("/")
	public String add(@ModelAttribute User user) {
		users.add(user);
		return "redirect:./";
	}
	
	@GetMapping("/{index}")
	public String getUpdate(@PathVariable("index") int index, Model model) {
		 User user = users.get(index);
		 model.addAttribute("user", user);
		 model.addAttribute("users", users);
		 model.addAttribute("index", index);
		 model.addAttribute("_method", "PUT");
		 model.addAttribute("submitButtonName", "修改");
		 
		 return "session11/user";
	}
	
	@PutMapping("/{index}")
	public String update(@ModelAttribute User user, @PathVariable("index") int index) {
		users.set(index, user);
		return "redirect:./";
	}
	
	@DeleteMapping("/{index}")
	public String delete(@PathVariable("index") int index) {
		users.remove(index);
		return "redirect:./";
	}
	
	
	
}
