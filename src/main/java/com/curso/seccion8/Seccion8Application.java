package com.curso.seccion8;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Seccion8Application implements CommandLineRunner {

	// se crea una variable
	private static  Logger logMymessage = LoggerFactory.getLogger(Seccion8Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Seccion8Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logMymessage.info("hola soy el logger... jijiji");

	}

}
