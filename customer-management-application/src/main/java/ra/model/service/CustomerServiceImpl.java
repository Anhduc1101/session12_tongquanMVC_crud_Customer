package ra.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dao.ICustomerDAO;
import ra.model.entity.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    ICustomerDAO customerDAO;

    @Override
    public List<Customer> findByName(String name) {
        return customerDAO.findAll();
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public Customer findById(Integer integer) {
        return customerDAO.findById(integer);
    }

    @Override
    public boolean save(Customer customer) {
        return customerDAO.save(customer);
    }

    @Override
    public void delete(Integer integer) {
        customerDAO.delete(integer);
    }
}
