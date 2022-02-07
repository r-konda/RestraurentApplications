package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * this is the task given to learn Spring and Hibernate.
 * @author rkonda
 *
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.demo.controller", "com.example.demo.dao", "com.example.demo.model", "com.example.demo.service"})
public class RestraurentApplication 
{

	public static void main(String[] args) 
	{
		
		SpringApplication.run(RestraurentApplication.class, args);
	}

}
