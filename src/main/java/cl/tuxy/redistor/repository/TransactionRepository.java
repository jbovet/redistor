package cl.tuxy.redistor.repository;

import cl.tuxy.redistor.model.Transaction;

import java.util.Map;


public interface TransactionRepository {

    void save(Transaction transaction);

    Transaction find(String referenceId);

    Map<String, Transaction> findAll();

    void update(Transaction transaction);

    void delete(String referenceId);

    long count();

    boolean exists(String referenceId);

}
