package com.aluracursos.literatura;

import com.aluracursos.literatura.principal.Principal;
import com.aluracursos.literatura.repository.IAutorRepository;
import com.aluracursos.literatura.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {


	@Autowired
	private IRepository repository;
	@Autowired
	private IAutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}
	@Override
	public void run(String... args) {

		Principal principal = new Principal(repository, autorRepository);
		principal.MostrarMenu();
	}


}
