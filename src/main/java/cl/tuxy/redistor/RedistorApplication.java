package cl.tuxy.redistor;

import cl.tuxy.redistor.model.Transaction;
import cl.tuxy.redistor.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootApplication
@RestController
public class RedistorApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RedistorApplication.class, args);
	}

	@Autowired
	TransactionRepository transactionRepository;

	@GetMapping("/transactions")
	public ResponseEntity<Transaction> list(){
		Map<String, Transaction> transactions = transactionRepository.findAll();
		return new ResponseEntity(transactions, HttpStatus.OK);
	}

	@Override
	public void run(String... args) {
		IntStream.range(0, 10).forEach(
				nbr -> transactionRepository.save(new Transaction(UUID.randomUUID().toString(),  new Random().nextInt(), UUID.randomUUID().toString(), UUID.randomUUID().toString()))
		);



	}
}
