package vn.edu.iuh.fit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.entities.Candidate;


@SpringBootApplication
public class WwwWeek03Application {
//	@Autowired
//	private CandidateServices candidateServices;
//
	public static void main(String[] args) {
		SpringApplication.run(WwwWeek03Application.class, args);
	}
//
//	@Bean
//	CommandLineRunner test() {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
////				Address address = new Address(UUID.fromString("f1fd5b76-0491-476e-ac85-1a613d25b24e"));
////				Candidate candidate = new Candidate(UUID.randomUUID(), LocalDate.now(), "a1@gmail.com", "Nguyen Van A", "087654321", address);
////
////				boolean b = candidateServices.create(candidate);
////				System.out.println("Insert candidate: " + b);
//
//				List<Candidate> candidates = candidateServices.getAll();
//
//				System.out.println(candidates);
//			}
//		};
//    }
}
