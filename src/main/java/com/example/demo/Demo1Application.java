package com.example.demo;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Demo1Application {

//	@Autowired
//	private Environment env;

	private  String cloudName = "santhoshfsev";

	private  String apiKey = "816142554865616";

	private  String apiSecret = "vBtzbHeA0VToBOiWgORyxQXYOjY";


	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);


	}

	@Bean
	public Cloudinary cloudinaryConfig() {

		System.out.println(" Api details" + cloudName + apiKey + apiSecret);
		Cloudinary cloudinary;
		Map config = new HashMap();
		config.put("cloud_name", cloudName);
		config.put("api_key", apiKey);
		config.put("api_secret", apiSecret);
		cloudinary = new Cloudinary(config);
		return cloudinary;
	}

}
	