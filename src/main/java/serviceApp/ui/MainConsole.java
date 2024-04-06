package serviceApp.ui;

import serviceApp.domain.Client;
import serviceApp.service.CarService;
import serviceApp.service.ClientService;

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
                    uiManager.afiseaza("Meniu Car");
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

    public void runClientConsole() {
        int optiune = uiManager.optionClientMenu();
        while (optiune != 0) {
            switch (optiune) {
                case 1:
                    List<Client> list = clientService.showClientList();
                    uiManager.afiseazaObiecte(list);
                    break;
                case 2:
                    uiManager.afiseaza("Adauga un client nou");
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
