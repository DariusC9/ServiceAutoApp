package serviceApp.ui;


import serviceApp.domain.Client;
import serviceApp.domain.Car;
import serviceApp.service.CarService;
import serviceApp.service.ClientService;

import java.time.LocalDate;
import java.util.List;

public class MainConsole {
    private ClientService clientService;
    private UIManager uiManager;
    private CarService carService;

    public MainConsole() {
    }

    public MainConsole(ClientService clientService,
                       CarService carService,
                       UIManager uiManager) {
        this.clientService = clientService;
        this.carService = carService;
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
                    uiManager.afiseaza("Meniu Tranzactie");
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
                    uiManager.afiseaza("Add Car");
                    int idNewCar = uiManager.citIntreg();
                    boolean idValidated = carService.validateCarId(idNewCar);
                    while (!idValidated) {
                        uiManager.afiseaza("Duplicate or invalid ID!");
                        idNewCar = uiManager.citIntreg();
                        idValidated = carService.validateCarId(idNewCar);
                    }
                    String model = uiManager.cititString();
                    int yearAcquisition = uiManager.citIntreg(); // needs validation
                    float numKm = uiManager.citFloat();
                    uiManager.afiseaza("Add yes or no for waranty");
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
                    uiManager.afiseaza("Update Car Info");
                    break;
                case 4:
                    uiManager.afiseaza("Delete Car from List");
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
                    boolean idValidated = carService.validateCarId(idNewClient);
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
                    uiManager.afiseaza("Actualizeaza un client");
                    break;
                case 4:
                    uiManager.afiseaza("Sterge un client");
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
}
