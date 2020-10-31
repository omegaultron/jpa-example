package com.practice.jpaexample;

import com.practice.jpaexample.entity.Customer;
import com.practice.jpaexample.repository.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataJpaExampleApplication {
	private static final Logger log = LogManager.getLogger(SpringDataJpaExampleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository customerRepository) {
		return (args -> {
			// save a few customers
			customerRepository.save(new Customer("Nagesh", "Gote"));
			customerRepository.save(new Customer("Sandeep", "Misal"));
			customerRepository.save(new Customer("Aman", "Pathan"));
			customerRepository.save(new Customer("Bhimsing", "Rajput"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer: customerRepository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = customerRepository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Pathan'):");
			log.info("---------------------------------------------");
			customerRepository.findByLastName("Pathan").forEach(pathan -> {
				log.info(pathan.toString());
			});
//			for (Customer pathan : customerRepository.findByLastName("Pathan")) {
//				log.info(pathan.toString());
//			}
			log.info("");
		});
	}
}
