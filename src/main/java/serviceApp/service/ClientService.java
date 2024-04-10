package serviceApp.service;

import serviceApp.domain.Car;
import serviceApp.domain.Client;
import serviceApp.repository.Repository;

import java.util.List;

public class ClientService {
    private Repository<Client> repository;
    public ClientService() { }
    public ClientService(Repository<Client> repository) {
        this.repository = repository;
        this.repository.save(Client.testClient);
        this.repository.save(Client.testClient);
    }

    public List<Client> showClientList() {
        return repository.findAll();
    }

    public void addNewClient(Client newClient) {
        repository.save(newClient);
    }

    public void updateCar(Client updateClient) {
        repository.update(updateClient);
    }

    public boolean validateClientId (int idNewClient) {
        List<Client> clientList = repository.findAll();
        for (Client client : clientList) {
            if (client.getId() == idNewClient) {
                return false;
            }
        }
        return true;
    }
}
