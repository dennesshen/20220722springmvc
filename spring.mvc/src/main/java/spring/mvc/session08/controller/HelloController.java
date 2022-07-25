package spring.mvc.session08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session08.entity.User;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@RequestMapping("/welcome")
	@ResponseBody
	public String welcome() {

		return "Welcome SpringMVC";
	}
	
	@RequestMapping(value = "/create/user")
	@ResponseBody
	public User createUser(@RequestBody User user) {
		System.out.println(user);
		return user;
	}
	
	/*
	 * 8. 路徑參數 @PathVariable
	 * 執行路徑: /mvc/hello/exam/75 -> 印出結果: 75 pass
	 * 執行路徑: /mvc/hello/exam/45 -> 印出結果: 45 fail
	 * */
	@RequestMapping(value = "/exam/{score}")
	@ResponseBody
	public String examScore(@PathVariable("score") Integer score) {
		return String.format("%d %s", score, (score>=60)?"pass":"fail");
	}
	
	/*
	 * 9. 路徑參數 @PathVariable (萬用字元: * 任意多字, ? 任意一字)
	 * 執行路徑: /mvc/hello/any/abc/java8
	 * 執行路徑: /mvc/hello/any/defghi/java9
	 * */
	@RequestMapping(value = "/any/*/java?")
	@ResponseBody
	public String any() {
		return "Hello any";
	}
	
	/*
	 * 10. @RequestParam + @PathVariable (Lab 練習)
	 * 子路徑：/mvc/hello/calc/add?x=30&y=20  -> Result：50
	 * 子路徑：/mvc/hello/calc/sub?x=30&y=20  -> Result：10
	 * 子路徑：/mvc/hello/calc/sub?y=20       -> Result：-20
	 * 子路徑：/mvc/hello/calc/add            -> Result：0
	 */
	// 請設計方法 api
	
	@RequestMapping(value = "/calc/{exp}")
	@ResponseBody
	public String calcExp(@PathVariable("exp") String exp,
						  @RequestParam(value = "x", required = false, defaultValue = "0") Integer x,
						  @RequestParam(value = "y", required = false, defaultValue = "0") Integer y) {
		int result = 0;
		switch (exp) {
			case "add":
				result = x + y;
				break;
			case "sub":
				result = x - y;
				break;
			default:
				return "exp path value error!";
		}
		return String.format("Result: %d", result);
	}
	
}
