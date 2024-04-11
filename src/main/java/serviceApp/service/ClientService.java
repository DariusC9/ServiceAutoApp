package serviceApp.service;

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
}
