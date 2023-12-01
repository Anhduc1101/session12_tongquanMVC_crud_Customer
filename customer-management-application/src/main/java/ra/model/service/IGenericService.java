package ra.model.service;

import java.util.List;

public interface IGenericService<T,ID> {
    List<T> findAll();
    T findById(ID id);
    boolean save(T t);
    void delete(ID id);
}
