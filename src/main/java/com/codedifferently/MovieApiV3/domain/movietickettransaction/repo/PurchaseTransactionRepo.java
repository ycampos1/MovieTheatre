package com.codedifferently.MovieApiV3.domain.movietickettransaction.repo;

import com.codedifferently.MovieApiV3.domain.movietickettransaction.model.PurchaseTransaction;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseTransactionRepo extends CrudRepository<PurchaseTransaction, Long > {
}
