package serviceApp.service;

import serviceApp.domain.Car;
import serviceApp.domain.Transaction;
import serviceApp.repository.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TransactionService {
    private Repository<Transaction> transactionRepository;
    private CarService carService;
    private ClientService clientService;

    // Constructors
    public TransactionService() {
    }

    public TransactionService(Repository<Transaction> transactionRepository, CarService carService, ClientService clientService) {
        this.transactionRepository = transactionRepository;
        this.carService = carService;
        this.clientService = clientService;

        // Pentru TEST
        this.transactionRepository.save(Transaction.testTransaction);
    }

    // Display all transactions
    public List<Transaction> showTransactionList() {
        return transactionRepository.findAll();
    }

    // Validate unique ID
    public boolean validateTransactionId(int idNewTransaction) {
        List<Transaction> transactionList = transactionRepository.findAll();
        for (Transaction transactionElement : transactionList) {
            if (transactionElement.getId() == idNewTransaction) {
                return false;
            }
        }
        return true;
    }

    // Validate existence of Client Card ID in database
    public boolean validateTransactionClientId(int idClient) {
        return !clientService.validateClientId(idClient);
    }

    // Validate existence of Car ID in database
    public boolean validateTransactionCarId(int idCar) {
        return !carService.validateCarId(idCar);
    }

    public void addNewTransaction(Transaction transaction) {
        transaction.setTotalCost(calculateTotalCost(transaction));
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

    private float calculateTotalCost(Transaction transaction) {
        float totalCost = 0;
        List<Car> carList = carService.showCarList();
        for (Car car : carList) {
            if (car.getId() == transaction.getId_car()) {
                if (!car.isHasWaranty()) {
                    totalCost += transaction.getPartsPrice();
                }
                break;
            }
        }
        if (transaction.getId_client() == 0) {
            totalCost += transaction.getWorkPrice();
        } else {
            totalCost += (float) (transaction.getWorkPrice() * 0.9);
        }

        transaction.setTotalCost(totalCost);
        return totalCost;
    }

    public List<Transaction> sortListDescendingOrder() {
        List<Transaction> transactionList = transactionRepository.findAll();
        Collections.sort(transactionList, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                float cost1 = calculateTotalCost(t1);
                float cost2 = calculateTotalCost(t2);
                return Float.compare(cost2, cost1);
            }
        });
        return transactionList;
    }

    public void displayCostListRange(int lowerRange, int upperRange) {
        List<Transaction> transactionList = transactionRepository.findAll();
        for (Transaction transaction : transactionList) {
            if (transaction.getTotalCost() >= lowerRange && transaction.getTotalCost() <= upperRange) {
                System.out.println(transaction);
            }
        }
    }
}
