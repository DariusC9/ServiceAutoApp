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
                    uiManager.afiseaza("Meniu Car");
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
}
