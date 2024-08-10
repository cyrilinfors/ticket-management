package com.org.ticketmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.org.ticketmanagement.config.TicketmanagementConfig;

 
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@Import({TicketmanagementConfig.class
	//, SecurityConfig.class 
	})
public class TicketManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementApplication.class, args);
	}

}
