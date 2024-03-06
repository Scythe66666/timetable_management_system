package com.example.form_creation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class FormCreationApplication {

	@GetMapping("/home")
	public String getMethodName(@RequestParam(required = false) String param) {
		return "home";
	}
	

	public static void main(String[] args) throws Exception {
				SpringApplication.run(FormCreationApplication.class, args);
	}
}
