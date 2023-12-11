package com.scalar.splitwise;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.scalar.splitwise.Commands.CommandExecutor;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner{

	@Autowired
	private CommandExecutor commandExecutor;
	
	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		while(true){
			String input = scanner.next();
			commandExecutor.execute(input);
		}
	}

}
