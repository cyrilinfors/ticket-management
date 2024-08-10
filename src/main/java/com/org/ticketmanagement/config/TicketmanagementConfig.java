package com.org.ticketmanagement.config;
  
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaAuditing
@EntityScan("com.org.ticketmanagement.entity")
@EnableJpaRepositories("com.org.ticketmanagement.*") 
@ComponentScan( 
		basePackages ={"com.org.ticketmanagement.*"})
																 
public class TicketmanagementConfig {
	  @Bean(value = "Ticketmanagement-modelmapper")
	  @Scope(value = "singleton")
	  public ModelMapper modelMapper() {
	    ModelMapper modelMapper = new ModelMapper();
	    return modelMapper;
	  }

	
}