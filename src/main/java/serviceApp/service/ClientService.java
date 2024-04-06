package serviceApp.service;

import serviceApp.domain.Client;
import serviceApp.repository.Repository;

public class ClientService {
    private Repository<Client> repository;
    public ClientService() { }
    public ClientService(Repository<Client> repository) {
        this.repository = repository;
    }
}
