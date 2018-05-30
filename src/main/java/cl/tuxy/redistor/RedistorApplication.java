package cl.tuxy.redistor;

import cl.tuxy.redistor.model.Transaction;
import cl.tuxy.redistor.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootApplication
@RestController
@RequestMapping(value = "/transactions")
public class RedistorApplication implements CommandLineRunner {

    @Autowired
    TransactionRepository transactionRepository;

    public static void main(String[] args) {
        SpringApplication.run(RedistorApplication.class, args);
    }

    @GetMapping
    public ResponseEntity<Transaction> list() {
        Map<String, Transaction> transactions = transactionRepository.findAll();
        return new ResponseEntity(transactions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> add(@RequestBody @Valid Transaction transaction) {
        transactionRepository.save(transaction);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Transaction> update(@RequestBody @Valid Transaction transaction) {
        transactionRepository.save(transaction);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> get(@PathVariable("id") String id) {
        Transaction transaction = transactionRepository.find(id);
        if (transaction == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity(transaction, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> delete(@PathVariable("id") String id) {
        Transaction transaction = transactionRepository.find(id);
        if (transaction == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        transactionRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity count() {
        return new ResponseEntity(transactionRepository.count(), HttpStatus.OK);
    }
    
    @Override
    public void run(String... args) {
        IntStream.range(0, 10).forEach(
                nbr -> transactionRepository.save(new Transaction(UUID.randomUUID().toString(), new Random().nextInt(), UUID.randomUUID().toString(), UUID.randomUUID().toString()))
        );
    }
}
