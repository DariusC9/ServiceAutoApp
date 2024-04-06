package serviceApp.repository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T extends Object> implements Repository<T> {
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
    }

    @Override
    public void delete(T object) { database.remove(object); }
}