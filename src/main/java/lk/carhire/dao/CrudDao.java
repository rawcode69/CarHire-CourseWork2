package lk.carhire.dao;

import java.util.ArrayList;
import java.util.List;

public interface CrudDao <T, ID> extends SuperDao{

    public Integer add(T t) throws Exception;

    public void update(T t) throws Exception;
    public void delete(T t) throws Exception;

    public T get(ID id) throws Exception;

    public List<T> getAll() throws Exception;
}
