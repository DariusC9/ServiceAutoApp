package serviceApp.service;

import serviceApp.domain.Car;
import serviceApp.domain.Transaction;
import serviceApp.repository.Repository;

import java.util.List;

public class TransactionService {
    private Repository<Transaction> transactionRepository;

    public TransactionService() {
    }

    public TransactionService(Repository<Transaction> transactionRepository) {
        this.transactionRepository = transactionRepository;

        // Pentru TEST
        this.transactionRepository.save(Transaction.testTransaction);
    }

    public List<Transaction> showTransactionList() {
        return transactionRepository.findAll();
    }

    public boolean validateTransactionId (int idNewTransaction) {
        List<Transaction> transactionList = transactionRepository.findAll();
        for (Transaction transactionElement : transactionList) {
            if (transactionElement.getId() == idNewTransaction) {
                return false;
            }
        }
        return true;
    }

    public void addNewTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void updateTransaction(Transaction updateTransaction) {
        transactionRepository.update(updateTransaction);
    }

    public void deleteTransaction(int idDeleteTransaction) {
        List<Transaction> transactionList = transactionRepository.findAll();
        for (Transaction element : transactionList) {
            if (element.getId() == idDeleteTransaction) {
                transactionRepository.delete(element);
                break;
            }
        }
    }
}
