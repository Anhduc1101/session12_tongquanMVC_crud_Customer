package ra.model.dao;

import ra.model.entity.Customer;

import java.util.List;

public interface ICustomerDAO extends IGenericDAO<Customer,Integer> {
    List<Customer> findByName(String name);

}
