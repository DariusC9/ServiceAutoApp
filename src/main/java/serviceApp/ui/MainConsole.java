package serviceApp.ui;


import serviceApp.domain.Client;
import serviceApp.domain.Car;
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

    public void runMainConsole() {
        int optiune = uiManager.optiuneMeniuPrincipal();
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
                    uiManager.afiseaza("Meniu Prelucrare");
                    break;
                case 0:
                    uiManager.afiseaza("Program finalizat");
                    break;
                default:
                    uiManager.afiseaza("Introduceti o optiune valida");
            }
            optiune = uiManager.optiuneMeniuPrincipal();
        }
    }

    // Car Menu Console (when closed, go back to main menu
    public void runCarConsole() {
        int optiune = uiManager.optionCarMenu();
        while (optiune != 0) {
            switch (optiune) {
                case 1:
                    uiManager.afiseaza("Add new Car data:");
                    uiManager.afiseaza("Unique ID: ");
                    int idNewCar = uiManager.citIntreg();
                    boolean idValidated = carService.validateCarId(idNewCar);
                    while (!idValidated) {
                        uiManager.afiseaza("Duplicate or invalid ID!");
                        idNewCar = uiManager.citIntreg();
                        idValidated = carService.validateCarId(idNewCar);
                    }
                    uiManager.afiseaza("Car model: ");
                    String model = uiManager.cititString();
                    uiManager.afiseaza("Year of acquisition: ");
                    int yearAcquisition = uiManager.citIntreg(); // needs validation
                    uiManager.afiseaza("Number of kilometers: ");
                    float numKm = uiManager.citFloat();
                    uiManager.afiseaza("Does the car have waranty? (yes/no) ");
                    String waranty = uiManager.cititString();
                    boolean hasWaranty;
                    if (waranty.equals("yes") || waranty.equals("y")) {
                        hasWaranty = true;
                    } else {
                        hasWaranty = false;
                    }
                    carService.addNewCar(new Car(idNewCar, model, yearAcquisition, numKm, hasWaranty));
                    break;
                case 2:
                    uiManager.afiseaza("Show Cars List");
                    List<Car> carList = carService.showCarList();
                    uiManager.afiseazaObiecte(carList);
                    break;
                case 3:
                    uiManager.afiseaza("Insert the ID for the car that gets updated: ");
                    int idUpdateCar = uiManager.citIntreg();
                    boolean idUpdateValidated = carService.validateCarId(idUpdateCar);
                    if (idUpdateValidated) {
                        uiManager.afiseaza("The inserted ID was not found in the list!");
                        break;
                    }
                    uiManager.afiseaza("Model: ");
                    String modelUpdate = uiManager.cititString();
                    uiManager.afiseaza("Year: ");
                    int yearAcquisitionUpdate = uiManager.citIntreg();   // needs validation
                    uiManager.afiseaza("Number of Km: ");
                    float numKmUpdate = uiManager.citFloat();
                    uiManager.afiseaza("Has waranty (yes/no): ");
                    String warantyUpdate = uiManager.cititString();     // needs validation
                    boolean hasWarantyUpdate;
                    if (warantyUpdate.equals("yes") || warantyUpdate.equals("y")) {
                        hasWarantyUpdate = true;
                    } else {
                        hasWarantyUpdate = false;
                    }
                    Car updateCar = new Car(idUpdateCar, modelUpdate, yearAcquisitionUpdate, numKmUpdate, hasWarantyUpdate);
                    carService.updateCar(updateCar);
                    break;
                case 4:
                    uiManager.afiseaza("Insert the ID of the Car to be deleted: ");
                    int idDeleteCar = uiManager.citIntreg();
                    boolean idDeleteValidated = carService.validateCarId(idDeleteCar);
                    if (idDeleteValidated) {
                        uiManager.afiseaza("The inserted ID was not found in the list!");
                        break;
                    }
                    carService.deleteCar(idDeleteCar);
                    break;
                case 0:
                    uiManager.afiseaza("Close Car Menu");
                    break;
                default:
                    uiManager.afiseaza("Choose a valid option!");
            }
            optiune = uiManager.optionCarMenu();
        }
    }

    public void runClientConsole() {
        int optiune = uiManager.optionClientMenu();
        while (optiune != 0) {
            switch (optiune) {
                case 1:
                    List<Client> list = clientService.showClientList();
                    uiManager.afiseazaObiecte(list);
                    break;
                case 2:
                    uiManager.afiseaza("Add a new card client");
                    uiManager.afiseaza("Add an unique id");
                    int idNewClient = uiManager.citIntreg();
                    boolean idValidated = clientService.validateClientId(idNewClient);
                    while (!idValidated) {
                        uiManager.afiseaza("Duplicate or invalid ID!");
                        idNewClient = uiManager.citIntreg();
                        idValidated = carService.validateCarId(idNewClient);
                    }
                    uiManager.afiseaza("Add last name");
                    String lastName = uiManager.cititString();
                    uiManager.afiseaza("Add first name");
                    String firstName = uiManager.cititString();
                    uiManager.afiseaza("Add a cnp");
                    double cnp = uiManager.citDouble(); // needs validation
                    LocalDate birthday = uiManager.addDate();
                    LocalDate registrationDate = uiManager.addDate();
                    clientService.addNewClient(new Client(idNewClient, lastName, firstName, cnp, birthday, registrationDate));
                    break;
                case 3:
                    uiManager.afiseaza("Insert the ID for the client that gets updated:  ");
                    int idUpdateClient = uiManager.citIntreg();
                    boolean idUpdateValidated = clientService.validateClientId(idUpdateClient);
                    while (idUpdateValidated) {
                        uiManager.afiseaza("Duplicate or invalid ID!");
                        idUpdateClient = uiManager.citIntreg();
                        idUpdateValidated = clientService.validateClientId(idUpdateClient);
                    }
                    uiManager.afiseaza("Add last name");
                    String lastNameUpdate = uiManager.cititString();
                    uiManager.afiseaza("Add first name");
                    String firstNameUpdate = uiManager.cititString();
                    uiManager.afiseaza("Add a cnp");
                    double cnpUpdate = uiManager.citDouble(); // needs validation
                    LocalDate birthdayUpdate = uiManager.addDate();
                    LocalDate registrationDateUpdate = uiManager.addDate();
                    clientService.updateCar(new Client(idUpdateClient, lastNameUpdate, firstNameUpdate, cnpUpdate, birthdayUpdate, registrationDateUpdate));
                    break;
                case 4:
                    uiManager.afiseaza("Insert the ID of the Client to be deleted: ");
                    int idDeleteClient = uiManager.citIntreg();
                    boolean idDeleteValidated = clientService.validateClientId(idDeleteClient);
                    if (idDeleteValidated) {
                        uiManager.afiseaza("The inserted ID was not found in the list!");
                        break;
                    }
                    clientService.deleteClient(idDeleteClient);
                    break;
                case 0:
                    uiManager.afiseaza("Close Client Menu");
                    break;
                default:
                    uiManager.afiseaza("Introduceti o optiune valida");
            }
            optiune = uiManager.optionClientMenu();
        }
    }

    private void runTransactionConsole() {
    }
}
