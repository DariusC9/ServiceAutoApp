package serviceApp.ui;

import serviceApp.service.CarService;
import serviceApp.service.ClientService;

public class MainConsole {

    private ClientService clientService;
    private CarService carService;

    public MainConsole() {
    }
    public MainConsole(ClientService clientService, CarService carService) {
        this.clientService = clientService;
        this.carService = carService;
    }

    public void runMainConsole() {
        System.out.println("Heyooo");
    }
}
