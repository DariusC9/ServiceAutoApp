package serviceApp;

import serviceApp.domain.Car;
import serviceApp.domain.Client;
import serviceApp.repository.InMemoryRepository;
import serviceApp.repository.Repository;
import serviceApp.service.CarService;
import serviceApp.service.ClientService;
import serviceApp.ui.MainConsole;
import serviceApp.ui.UIManager;

public class Main {
    public static void main(String[] args) {
        Repository<Car> carRepository = new InMemoryRepository<>();
        Repository<Client> clientRepository = new InMemoryRepository<>();
        CarService carService = new CarService(carRepository);
        ClientService clientService = new ClientService(clientRepository);
        UIManager uiManager = new UIManager();
        MainConsole mainConsole = new MainConsole(clientService,carService, uiManager);
        mainConsole.runMainConsole();
    }
}
