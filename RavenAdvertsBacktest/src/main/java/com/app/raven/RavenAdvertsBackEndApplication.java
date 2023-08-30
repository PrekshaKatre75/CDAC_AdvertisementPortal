package com.app.raven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@SpringBootApplication
public class RavenAdvertsBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(RavenAdvertsBackEndApplication.class, args);
	}

	@Bean // <bean id , class , scope ...../>
	public ModelMapper myModelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper; 
	}
}
