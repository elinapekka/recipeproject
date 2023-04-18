package com.example.RecipesByElina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class RecipesByElinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesByElinaApplication.class, args);
	}
	
	/*
	@Bean
	public CommandLineRunner customerDemo(JdbcTemplate template) {
		return (args) -> {
			
			// create users table
			template.update("CREATE TABLE Users("
					+ "user_id int NOT NULL AUTO_INCREMENT, "
					+ "username varchar(50) NOT NULL, "
					+ "password varchar(255) NOT NULL, "
					+ "email varchar(100) NOT NULL, "
					+ "role varchar(30) NOT NULL, "
					+ "PRIMARY KEY(user_id), "
					+ "UNIQUE(username), "
					+ "UNIQUE(email))");
			
			// add users to the user table 
			template.update("INSERT INTO Users(username, password, email, role) VALUES (?, ?, ?, ?)", "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@email.com", "USER");
			template.update("INSERT INTO Users(username, password, email, role) VALUES (?, ?, ?, ?)", "admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@email.com", "ADMIN");
			
			// create recipes table
			template.update("CREATE TABLE Recipes (recipe_id int NOT NULL AUTO_INCREMENT, "
					+ "name varchar(50) NOT NULL, "
					+ "time_amount int, "
					+ "short_description varchar(200), "
					+ "PRIMARY KEY (recipe_id));");
			

			// create units table
			template.update("CREATE TABLE Units("
					+ "unit_id int NOT NULL AUTO_INCREMENT, "
					+ "unit varchar(30) NOT NULL, "
					+ "PRIMARY KEY (unit_id), "
					+ "UNIQUE (unit))");
			
			// create ingredients table
			template.update("CREATE TABLE Ingredients ("
					+ "ingredient_id int NOT NULL AUTO_INCREMENT, "
					+ "recipe_id int NOT NULL, "
					+ "ingredient varchar(75) NOT NULL, "
					+ "quantity varchar(50), "
					+ "unit varchar(30), "
					+ "PRIMARY KEY (ingredient_id), "
					+ "CONSTRAINT FK_Ingredients_Recipes FOREIGN KEY (recipe_id) "
					+ "REFERENCES Recipes(recipe_id), "
					+ "CONSTRAINT FK_Ingredients_Units FOREIGN KEY (unit) "
					+ "REFERENCES Units(unit) ON DELETE CASCADE ON UPDATE CASCADE);");
			
			// create instructions table
			template.update("CREATE TABLE Instructions ("
					+ "instruction_id int NOT NULL AUTO_INCREMENT, "
					+ "recipe_id int NOT NULL, "
					+ "step_num int NOT NULL, "
					+ "description varchar(300), "
					+ "PRIMARY KEY (instruction_id), "
					+ "CONSTRAINT FK_Instructions_Recipes FOREIGN KEY (recipe_id) "
					+ "REFERENCES Recipes(recipe_id)"
					+ ");");
		};
	};
	*/
}

