package serviceApp.service;

import serviceApp.domain.Client;
import serviceApp.repository.Repository;

import java.util.ArrayList;
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

    public void deleteClient(int idDeleteClient) {
        List<Client> clientList = repository.findAll();
        for (Client client : clientList) {
            if (client.getId() == idDeleteClient) {
                repository.delete(client);
                break;
            }
        }
    }

    public List<Client> searchByLastName(String searchName) {
        List<Client> cardList = repository.findAll();
        List<Client> result = new ArrayList<>();
        for (Client clientElem : cardList) {
            if (clientElem.getLastName().equals(searchName)) {
                result.add(clientElem);
            }
        }
        return result;
    }
}
