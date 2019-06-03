package com.bank.operation.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bank.operation.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	Optional<Account> findByaccountNumber(Long accountNumber);
}
