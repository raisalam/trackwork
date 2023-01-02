package com.example.trackwork;


import com.example.trackwork.service.Cache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@SpringBootApplication
public class TrackWorkApplication {

	@RequestMapping("/")
	public String  home() {
		Cache.isAll = false;
		return "redirect:/index.html";
	}
	@RequestMapping("/all")
	public String  all() {
		Cache.isAll = true;
		return "redirect:/All.html";
	}
	public static void main(String[] args) {
		SpringApplication.run(TrackWorkApplication.class, args);
	}

}
