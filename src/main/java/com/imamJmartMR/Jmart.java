package com.imamJmartMR;

import com.imamJmartMR.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for starting the Jmart application Backend.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

@SpringBootApplication
public class Jmart {

    public static void main(String[] args) {

        JsonDBEngine.Run(Jmart.class);
        SpringApplication.run(Jmart.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));

    }
}
