package serviceApp.service;

import serviceApp.domain.Car;
import serviceApp.domain.Client;
import serviceApp.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class ClientService extends SimpleObjectService<Client> {
    public ClientService() { }
    public ClientService(Repository<Client> repository) {
        super(repository);
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

    public Client searchById(int idClient) {
        return repository.findById(idClient);
    }

    public boolean isCnpUnique(float cnp) {
        List<Client> clients = repository.findAll();
        if(clients.isEmpty()) {
            return true;
        }
        System.out.println(clients);
        for (Client client : clients) {
            if (client.getCnp() != cnp) {
                return true;
            }
        }
        return false;
    }
}
