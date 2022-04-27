package com.codedifferently.MovieApiV3.domain.movietickettransaction.exceptions;

import com.codedifferently.MovieApiV3.domain.movietickettransaction.model.PurchaseTransaction;

public class PurchaseTransactionException extends Exception{
    public PurchaseTransactionException(String msg){
        super(msg);
    }
}
