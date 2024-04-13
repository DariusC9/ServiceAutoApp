package serviceApp.ui;


import serviceApp.domain.Client;
import serviceApp.domain.Car;
import serviceApp.domain.Transaction;
import serviceApp.service.CarService;
import serviceApp.service.ClientService;
import serviceApp.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

public class MainConsole {
    private ClientService clientService;
    private CarService carService;
    private TransactionService transactionService;
    private UIManager uiManager;

    // Constructors
    public MainConsole() {
    }

    public MainConsole(ClientService clientService,
                       CarService carService,
                       TransactionService transactionService,
                       UIManager uiManager) {
        this.clientService = clientService;
        this.carService = carService;
        this.transactionService = transactionService;
        this.uiManager = uiManager;
    }

    // Main Menu Console (when closed, terminate program)
    public void runMainConsole() {
        int optiune = uiManager.optionMainMenu();
        while (optiune != 0) {
            switch (optiune) {
                case 1:
                    runCarConsole();
                    break;
                case 2:
                    runClientConsole();
                    break;
                case 3:
                    runTransactionConsole();
                    break;
                case 4:
                    uiManager.displayText("Search car");
                    String searchModel = uiManager.readString();
                    List<Car> resultsCar = carService.searchByCarModel(searchModel);
                    if(resultsCar.isEmpty()) {
                        uiManager.displayText("There are no car based on the model " + searchModel);
                    } else {
                        uiManager.displayObjects(resultsCar);
                    }
                    break;
                case 5:
                    uiManager.displayText("Search client by last name");
                    uiManager.displayText("Insert the name: ");
                    String searchName = uiManager.readString();
                    List<Client> resultsClient = clientService.searchByLastName(searchName);
                    if(resultsClient.isEmpty()) {
                        uiManager.displayText("There are no Cards for the inserted client name!" + searchName);
                    } else {
                        uiManager.displayObjects(resultsClient);
                    }
                    break;
                case 6:
                    uiManager.displayText("Display transactions in given range.");
                    uiManager.displayText("Insert lower range: ");
                    int lower = uiManager.readInt();
                    uiManager.displayText("Insert lower range: ");
                    int upper =uiManager.readInt();
                    transactionService.displayCostListRange(lower, upper);
                    break;
                case 7:
                    uiManager.displayText("Display client cards by discounted work price in descending order.");
                    List<Car> orderedList = transactionService.sortListDescendingOrder();
                    uiManager.displayObjects(orderedList);
                    break;
                case 8:
                    List<Client> orderedClients = transactionService.displayClientsCardDiscounts();
                    uiManager.displayObjects(orderedClients);
                    break;
                case 9:
                    carService.updateWaranty();
                    List<Car> carsUpdated = carService.showObjectList();
                    uiManager.displayObjects(carsUpdated);
                    break;
                case 0:
                    uiManager.displayText("End program");
                    break;
                default:
                    uiManager.displayText("Insert a valid option!");
            }
            optiune = uiManager.optionMainMenu();
        }
    }

    // Car Menu Console (when closed, go back to main menu)
    public void runCarConsole() {
        int optiune = uiManager.optionCarMenu();
        while (optiune != 0) {
            switch (optiune) {
                case 1:
                    uiManager.displayText("Add new Car data:");
                    uiManager.displayText("Unique ID: ");
                    int idNewCar = uiManager.readInt();
                    boolean idValidated = carService.validateObjectId(idNewCar);
                    while (!idValidated) {
                        uiManager.displayText("Duplicate or invalid ID!");
                        idNewCar = uiManager.readInt();
                        idValidated = carService.validateObjectId(idNewCar);
                    }
                    uiManager.displayText("Car model: ");
                    String model = uiManager.readString();
                    uiManager.displayText("Year of acquisition: ");
                    int yearAcquisition = uiManager.readInt(); // needs validation
                    uiManager.displayText("Number of kilometers: ");
                    float numKm = uiManager.readFloat();
                    uiManager.displayText("Does the car have waranty? (yes/no) ");
                    String waranty = uiManager.readString();
                    boolean hasWaranty;
                    if (waranty.equals("yes") || waranty.equals("y")) {
                        hasWaranty = true;
                    } else {
                        hasWaranty = false;
                    }
                    carService.addNewObject(new Car(idNewCar, model, yearAcquisition, numKm, hasWaranty));
                    break;
                case 2:
                    uiManager.displayText("Show Cars List");
                    List<Car> carList = carService.showObjectList();
                    uiManager.displayObjects(carList);
                    break;
                case 3:
                    uiManager.displayText("Insert the ID for the car that gets updated: ");
                    int idUpdateCar = uiManager.readInt();
                    boolean idUpdateValidated = carService.validateObjectId(idUpdateCar);
                    if (idUpdateValidated) {
                        uiManager.displayText("The inserted ID was not found in the list!");
                        break;
                    }
                    uiManager.displayText("Model: ");
                    String modelUpdate = uiManager.readString();
                    uiManager.displayText("Year: ");
                    int yearAcquisitionUpdate = uiManager.readInt();   // needs validation
                    uiManager.displayText("Number of Km: ");
                    float numKmUpdate = uiManager.readFloat();
                    uiManager.displayText("Has waranty (yes/no): ");
                    String warantyUpdate = uiManager.readString();     // needs validation
                    boolean hasWarantyUpdate;
                    if (warantyUpdate.equals("yes") || warantyUpdate.equals("y")) {
                        hasWarantyUpdate = true;
                    } else {
                        hasWarantyUpdate = false;
                    }
                    Car updateCar = new Car(idUpdateCar, modelUpdate, yearAcquisitionUpdate, numKmUpdate, hasWarantyUpdate);
                    carService.updateObject(updateCar);
                    break;
                case 4:
                    uiManager.displayText("Insert the ID of the Car to be deleted: ");
                    int idDeleteCar = uiManager.readInt();
                    boolean idDeleteValidated = carService.validateObjectId(idDeleteCar);
                    if (idDeleteValidated) {
                        uiManager.displayText("The inserted ID was not found in the list!");
                        break;
                    }
                    carService.deleteObject(idDeleteCar);
                    break;
                case 0:
                    uiManager.displayText("Close Car Menu");
                    break;
                default:
                    uiManager.displayText("Choose a valid option!");
            }
            optiune = uiManager.optionCarMenu();
        }
    }

    // Client Card Menu Console (when closed, go back to main menu)
    private void runClientConsole() {
        int optiune = uiManager.optionClientMenu();
        while (optiune != 0) {
            switch (optiune) {
                case 1:
                    uiManager.displayText("Add a new Client Card");
                    uiManager.displayText("Add an unique id");
                    int idNewClient = uiManager.readInt();
                    boolean idValidated = clientService.validateObjectId(idNewClient);
                    while (!idValidated) {
                        uiManager.displayText("Duplicate or invalid ID!");
                        idNewClient = uiManager.readInt();
                        idValidated = clientService.validateObjectId(idNewClient);
                    }
                    uiManager.displayText("Add last name");
                    String lastName = uiManager.readString();
                    uiManager.displayText("Add first name");
                    String firstName = uiManager.readString();
                    uiManager.displayText("Add a cnp");
                    float cnp = uiManager.readFloat();
                    boolean cnpValidated = clientService.isCnpUnique(cnp);
                    System.out.println(cnpValidated);
                    while (!cnpValidated) {
                        uiManager.displayText("The cnp must be unique");
                        cnp = uiManager.readFloat();
                        cnpValidated = clientService.isCnpUnique(cnp);
                    }
                    uiManager.displayText("Add birthday");
                    LocalDate birthday = uiManager.addDate();
                    uiManager.displayText("Add registration date");
                    LocalDate registrationDate = uiManager.addDate();
                    clientService.addNewObject(new Client(idNewClient, lastName, firstName, cnp, birthday, registrationDate));
                    break;
                case 2:
                    List<Client> list = clientService.showObjectList();
                    uiManager.displayObjects(list);
                    break;
                case 3:
                    uiManager.displayText("Insert the ID for the client that gets updated:  ");
                    int idUpdateClient = uiManager.readInt();
                    boolean idUpdateValidated = clientService.validateObjectId(idUpdateClient);
                    while (idUpdateValidated) {
                        uiManager.displayText("Duplicate or invalid ID!");
                        idUpdateClient = uiManager.readInt();
                        idUpdateValidated = clientService.validateObjectId(idUpdateClient);
                    }
                    uiManager.displayText("Add last name");
                    String lastNameUpdate = uiManager.readString();
                    uiManager.displayText("Add first name");
                    String firstNameUpdate = uiManager.readString();
                    uiManager.displayText("Add a cnp");
                    float cnpUpdate = uiManager.readFloat();
                    boolean cnpUpdateValidated = clientService.isCnpUnique(cnpUpdate);
                    System.out.println(cnpUpdateValidated);
                    while (!cnpUpdateValidated) {
                        uiManager.displayText("The cnp must be unique");
                        cnpUpdate = uiManager.readFloat();
                        cnpUpdateValidated = clientService.isCnpUnique(cnpUpdate);
                    }
                    LocalDate birthdayUpdate = uiManager.addDate();
                    LocalDate registrationDateUpdate = uiManager.addDate();
                    clientService.updateObject(new Client(idUpdateClient, lastNameUpdate, firstNameUpdate, cnpUpdate, birthdayUpdate, registrationDateUpdate));
                    break;
                case 4:
                    uiManager.displayText("Insert the ID of the Client to be deleted: ");
                    int idDeleteClient = uiManager.readInt();
                    boolean idDeleteValidated = clientService.validateObjectId(idDeleteClient);
                    if (idDeleteValidated) {
                        uiManager.displayText("The inserted ID was not found in the list!");
                        break;
                    }
                    clientService.deleteObject(idDeleteClient);
                    break;
                case 0:
                    uiManager.displayText("Close Client Menu");
                    break;
                default:
                    uiManager.displayText("Insert a valid option!");
            }
            optiune = uiManager.optionClientMenu();
        }
    }
    // Transaction Menu Console (when closed, go back to main menu)
    public void runTransactionConsole() {
        int optiune = uiManager.optionTransactionMenu();
        while (optiune != 0) {
            switch (optiune) {
                case 1:
                    uiManager.displayText("Add a new transaction");
                    uiManager.displayText("Add an unique id");
                    int idNewTrans = uiManager.readInt();
                    boolean idValidated = transactionService.validateTransactionId(idNewTrans);
                    while (!idValidated) {
                        uiManager.displayText("Duplicate or invalid ID!");
                        idNewTrans = uiManager.readInt();
                        idValidated = transactionService.validateTransactionId(idNewTrans);
                    }
                    uiManager.displayText("Add id car");
                    int idCar = uiManager.readInt();
                    boolean idCarValidated = transactionService.validateTransactionCarId(idCar);
                    while (!idCarValidated) {
                        uiManager.displayText("Needs a valid car id (car must be in the database)!");
                        idCar = uiManager.readInt();
                        idCarValidated = transactionService.validateTransactionCarId(idCar);
                    }
                    uiManager.displayText("Add id client. If there is no client enter 0");
                    int idClient = uiManager.readInt();
                    boolean idClientValidated = transactionService.validateTransactionClientId(idClient);
                    while (!idClientValidated && idClient != 0) {
                        uiManager.displayText("Needs a valid client id (client must be in database)!");
                        idClient = uiManager.readInt();
                        idClientValidated = transactionService.validateTransactionClientId(idClient);
                    }
                    uiManager.displayText("Add parts price");
                    float partsPrice = uiManager.readFloat();
                    uiManager.displayText("Add work price");
                    float workPrice = uiManager.readFloat();
                    uiManager.displayText("Add date repair date");
                    LocalDate dateHour = uiManager.addDate(); //TODO: NEEDS ERROR handling
                    transactionService.addNewTransaction(new Transaction(idNewTrans, idCar, idClient, partsPrice, workPrice, dateHour));
                    break;
                case 2:
                    List<Transaction> list = transactionService.showTransactionList();
                    uiManager.displayObjects(list);
                break;
                case 3:
                    uiManager.displayText("Insert the ID for the transaction that gets updated:  ");
                    int idUpdateTransaction = uiManager.readInt();
                    boolean idUpdateValidated = transactionService.validateTransactionId(idUpdateTransaction);
                    while (idUpdateValidated) {
                        uiManager.displayText("Duplicate or invalid ID!");
                        idUpdateTransaction = uiManager.readInt();
                        idUpdateValidated = transactionService.validateTransactionId(idUpdateTransaction);
                    }
                    uiManager.displayText("Add id car");
                    int idCarUpdate = uiManager.readInt(); // needs validation as per add  function
                    boolean idCarUpdateValidated = transactionService.validateTransactionCarId(idCarUpdate);
                    while (!idCarUpdateValidated) {
                        uiManager.displayText("Needs a valid car id (car must be in the database)!");
                        idCarUpdate = uiManager.readInt();
                        idCarUpdateValidated = transactionService.validateTransactionCarId(idCarUpdate);
                    }
                    uiManager.displayText("Add id client. If there is no client enter 0");
                    int idClientUpdate = uiManager.readInt(); // needs validation as per add  function
                    boolean idClientUpdateValidated = transactionService.validateTransactionClientId(idClientUpdate);
                    while (!idClientUpdateValidated && idClientUpdate != 0) {
                        uiManager.displayText("Needs a valid client id!");
                        idClientUpdate = uiManager.readInt();
                        idClientUpdateValidated = transactionService.validateTransactionClientId(idClientUpdate);
                    }
                    uiManager.displayText("Add parts price");
                    float partsPriceUpdate = uiManager.readFloat();
                    uiManager.displayText("Add work price");
                    float workPriceUpdate = uiManager.readFloat();
                    uiManager.displayText("Add repair date");
                    LocalDate dateHourUpdate = uiManager.addDate(); //TODO: NEEDS ERROR handling
                    transactionService.updateTransaction(new Transaction(idUpdateTransaction, idCarUpdate, idClientUpdate, partsPriceUpdate, workPriceUpdate, dateHourUpdate));
                    break;
                case 4:
                    uiManager.displayText("Insert the ID of the transaction to be deleted: ");
                    int idDeleteTransaction = uiManager.readInt();
                    boolean idDeleteValidated = transactionService.validateTransactionId(idDeleteTransaction);
                    if (idDeleteValidated) {
                        uiManager.displayText("The inserted ID was not found in the list!");
                        break;
                    }
                    transactionService.deleteTransaction(idDeleteTransaction);
                    break;
                case 0:
                    uiManager.displayText("Close Transaction Menu");
                    break;
                default:
                    uiManager.displayText("Introduceti o optiune valida");
            }
            optiune = uiManager.optionTransactionMenu();
        }
    }
}
