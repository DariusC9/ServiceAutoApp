package serviceApp.service;

import serviceApp.domain.Car;
import serviceApp.domain.Transaction;
import serviceApp.repository.Repository;

import java.util.List;

public class TransactionService {
    private Repository<Transaction> transactionRepository;
    private CarService carService;
    private ClientService clientService;
    public TransactionService() {
    }

    public TransactionService(Repository<Transaction> transactionRepository, CarService carService, ClientService clientService) {
        this.transactionRepository = transactionRepository;
        this.carService = carService;
        this.clientService = clientService;

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

    public boolean validateTransactionClientId(int idClient) {
        return !clientService.validateClientId(idClient);
    }

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
            totalCost += (transaction.getWorkPrice() * 0.9);
        }
        return totalCost;
    }
}
