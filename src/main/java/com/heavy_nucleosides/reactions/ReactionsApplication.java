package com.heavy_nucleosides.reactions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReactionsApplication {
	
	@Value("${import_path}")
	private String importPath;

	public static void main(String[] args) {
		SpringApplication.run(ReactionsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.print("Let's inspect import's files count:");

		    try (Stream<Path> walk = Files.walk(Paths.get(importPath))) { 

		        List<String> result = walk.filter(Files::isRegularFile)
		                .map(x -> x.toString()).collect(Collectors.toList());

		        System.out.println(result.size());
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		};
	}
}
