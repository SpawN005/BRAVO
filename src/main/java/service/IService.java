package service;

import java.util.List;

public interface IService <T>{
    void insert(T t);
    void delete(T t);
    void update(T t);
    List<T> readAll();
    T readById(int id);

}
