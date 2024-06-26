package serviceApp.repository;

import java.util.List;

public interface Repository<T>{
    public void save(T object);
    public List<T> findAll();
    public T findById(int id);
    public void update(T object);
    public void delete(T object);
}
