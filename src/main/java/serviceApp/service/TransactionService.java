package serviceApp.service;

import serviceApp.domain.Car;
import serviceApp.domain.Client;
import serviceApp.domain.Transaction;
import serviceApp.repository.Repository;

import java.time.LocalDate;
import java.util.*;

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
        return !clientService.validateObjectId(idClient);
    }

    // Validate existence of Car ID in database
    public boolean validateTransactionCarId(int idCar) {
        return !carService.validateObjectId(idCar);
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
        List<Car> carList = carService.showObjectList();
        for (Car car : carList) {
            if (car.getId() == transaction.getId_car()) {
                if (!car.isHasWaranty()) {
                    totalCost += transaction.getPartsPrice();
                }
                break;
            }
        }
        totalCost += calculateTotalWorkCost(transaction);
        return totalCost;
    }

    public List<Car> sortListDescendingOrder() {
        List<Transaction> transactionList = transactionRepository.findAll();
        List<Car> sortedCars = new ArrayList<>();
        Collections.sort(transactionList, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                float cost1 = calculateTotalWorkCost(t1);
                float cost2 = calculateTotalWorkCost(t2);
                return Float.compare(cost2, cost1);
            }
        });
       for (Transaction transaction : transactionList) {
           Car car = carService.searchById(transaction.getId_car());
           if (car != null) {
               sortedCars.add(car);
           }
       }
       return sortedCars;
    }

    private float calculateTotalWorkCost(Transaction transaction) {
        float result = 0;
        if (transaction.getId_client() == 0) {
            result += transaction.getWorkPrice();
        } else {
            result += (float) (transaction.getWorkPrice() * 0.9);
        }
        return result;
    }

    public void displayCostListRange(int lowerRange, int upperRange) {
        List<Transaction> transactionList = transactionRepository.findAll();
        for (Transaction transaction : transactionList) {
            if (transaction.getTotalCost() >= lowerRange && transaction.getTotalCost() <= upperRange) {
                System.out.println(transaction);
            }
        }
    }

    public List<Client> displayClientsCardDiscounts() {
        List<Transaction> transactionList = transactionRepository.findAll();
        List<Client> result = new ArrayList<>();
        HashMap<Integer, Float> map = new HashMap<>();
        for (Transaction transaction : transactionList) {
            float discount = 0;
            int idClient = transaction.getId_client();
            if (idClient != 0) {
                discount = (float) (transaction.getWorkPrice() * 0.1);
                if (map.containsKey(idClient)) {
                    float oldDiscountValue = map.get(idClient);
                    map.put(idClient, (oldDiscountValue + discount));
                } else {
                    map.put(idClient, discount);
                }
            }
        }
        List<Map.Entry<Integer, Float>> sortedList = new ArrayList<>(map.entrySet());
        sortedList.sort(Map.Entry.comparingByValue());

        for (Map.Entry<Integer, Float> entry : sortedList.reversed()) {
            Client client = clientService.searchById(entry.getKey());
            result.add(client);
        }
        return  result;
    }

    public void deleteTransactionsBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactionList = transactionRepository.findAll();
        Iterator<Transaction> iterator = transactionList.iterator();
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            LocalDate transactionDate = transaction.getDateHour();

            if (transactionDate.isAfter(startDate) && transactionDate.isBefore(endDate)) {
                iterator.remove();
            }
        }
    }
}
