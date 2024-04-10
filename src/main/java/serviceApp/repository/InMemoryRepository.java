package serviceApp.repository;

import serviceApp.domain.BaseId;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T extends BaseId> implements Repository<T> {
    List<T> database;

    public InMemoryRepository() {
        database = new ArrayList<>();
    }
    @Override
    public void save(T object) { database.add(object); }

    @Override
    public List<T> findAll() { return database; }

    @Override
    public void update(T object) {
        // object - o sa fie varianta finala pe care vrem sa o adaugam in database
        // cauti dupa id (object.getId() in database)
        // inlocuiesti cu object
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getId() == object.getId()) {
                database.set(i,object);
                System.out.println("DATA UPDATED");
            }
        }
    }

    @Override
    public void delete(T object) { database.remove(object); }
}
