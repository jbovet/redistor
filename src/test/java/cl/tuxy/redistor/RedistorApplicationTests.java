package cl.tuxy.redistor;

import cl.tuxy.redistor.model.Transaction;
import cl.tuxy.redistor.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedistorApplicationTests {


    @Autowired
    TransactionRepository repository;

    @Before
    public void setup(){
        assert repository != null;
    }

    @Test
    public void save(){
        String referenceId = "1234";
        repository.save(new Transaction(referenceId, 120000, "321z", "21"));
    }

    @Test
    public void find() {
        String referenceId = "1234";
        Transaction transaction = repository.find(referenceId);
        assert transaction != null;
        Assert.assertEquals(transaction.getReferenceId(),referenceId);
        assert transaction.getAmount() == 120000;
    }


    @Test
    public void findAll() {
        Assert.assertTrue(repository.findAll().size() > 0);
    }

    @Test
    public void update() {
        Transaction t = new Transaction("1234", 125000, "321z", "21");
        repository.update(t);
        Transaction t2 = repository.find("1234");
        Assert.assertEquals(t2.getAmount().intValue(),125000);
    }

    @Test
    public void delete() {
        repository.delete("1234");
    }





}
