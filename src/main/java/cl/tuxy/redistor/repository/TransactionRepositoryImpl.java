package cl.tuxy.redistor.repository;

import cl.tuxy.redistor.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private static final String KEY = "Transaction";

    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, String, Transaction> hashOperations;

    @Autowired
    public TransactionRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Transaction transaction) {
        hashOperations.put(KEY, transaction.getReferenceId(), transaction);
    }

    @Override
    public Transaction find(String referenceId) {
        return hashOperations.get(KEY, referenceId);
    }

    @Override
    public Map<String, Transaction> findAll() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void update(Transaction transaction) {
        hashOperations.put(KEY, transaction.getReferenceId(), transaction);
    }

    @Override
    public void delete(String referenceId) {
        hashOperations.delete(KEY, referenceId);
    }
}
