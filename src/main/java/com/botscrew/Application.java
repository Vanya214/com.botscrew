package com.botscrew;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class Application implements CommandLineRunner {


    private final CommandExecutor commandExecutor;

    public Application(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while(true){
            String query = scanner.nextLine();
            if(query.equals("Stop")){
                System.exit(0);
            }
            String result = commandExecutor.execute(query);
            System.out.println(result);
        }

    }
}
