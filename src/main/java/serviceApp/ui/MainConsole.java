package serviceApp.ui;

import serviceApp.service.CarService;
import serviceApp.service.ClientService;

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
                    uiManager.afiseaza("Meniu Client");
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
                    break;
                case 2:
                    uiManager.afiseaza("Show Cars List");
                    break;
                case 3:
                    uiManager.afiseaza("Update Car Info");
                    break;
                case 4:
                    uiManager.afiseaza("Delete Car from List");
                    break;
                case 0:
                    uiManager.afiseaza("Program ended!");
                    break;
                default:
                    uiManager.afiseaza("Choose a valid option!");
            }
            optiune = uiManager.optionCarMenu();
        }
    }
}
