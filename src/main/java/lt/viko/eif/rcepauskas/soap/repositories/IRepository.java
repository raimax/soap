package lt.viko.eif.rcepauskas.soap.repositories;

import java.util.List;

public interface IRepository<T> {
    <T> Object get(Integer id);
    <T> List<Object> getAll();
    void insert(T t);
    void update(T t);
    void delete(Integer id);
}
