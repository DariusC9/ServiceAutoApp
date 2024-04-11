package serviceApp.service;

import serviceApp.domain.BaseId;
import serviceApp.domain.Client;
import serviceApp.repository.Repository;

import java.util.List;

public abstract class SimpleObjectService<T extends BaseId> {
    protected Repository<T> repository;
    public SimpleObjectService() { }
    public SimpleObjectService(Repository<T> repository) {
        this.repository = repository;
    }
    public List<T> showObjectList() {
        return repository.findAll();
    }

    public void addNewObject(T newObject) {
        repository.save(newObject);
    }

    public void updateObject(T updateObject) {
        repository.update(updateObject);
    }

    public boolean validateObjectId (int idNewObject) {
        List<T> objectList = repository.findAll();
        for (T client : objectList) {
            if (client.getId() == idNewObject) {
                return false;
            }
        }
        return true;
    }

    public void deleteObject(int idDeleteObject) {
        List<T> objectList = repository.findAll();
        for (T object : objectList) {
            if (object.getId() == idDeleteObject) {
                repository.delete(object);
                break;
            }
        }
    }
}
