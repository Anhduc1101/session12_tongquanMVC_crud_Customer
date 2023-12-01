package ra.model.dao;

import org.springframework.stereotype.Component;
import ra.model.entity.Customer;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDAOImpl implements ICustomerDAO {
    @Override
    public List<Customer> findByName(String name) {
        List<Customer> customers = new ArrayList<>();
        Connection con = null;
        con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_by_name(?)");
            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return customers;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        Connection connection = ConnectionDB.openCon();
        try {
            CallableStatement cs = connection.prepareCall("call proc_show_list_customer()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(connection);
        }
        return customers;
    }

    @Override
    public Customer findById(Integer integer) {
        Customer customer = new Customer();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_by_id(?)");
            cs.setInt(1, integer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return customer;
    }

    @Override
    public boolean save(Customer customer) {
        Connection con = ConnectionDB.openCon();
        CallableStatement cs = null;
        int check;
        try {
            if (customer.getId()==0) {
                cs = con.prepareCall("call proc_create_customer(?,?,?)");
                cs.setString(1, customer.getName());
                cs.setString(2, customer.getAddress());
                cs.setString(3, customer.getEmail());
            } else {
                cs = con.prepareCall("call proc_update_customer(?,?,?,?)");
                cs.setString(1, customer.getName());
                cs.setString(2, customer.getAddress());
                cs.setString(3, customer.getEmail());
                cs.setInt(4, customer.getId());
            }
            check = cs.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return false;
    }

    @Override
    public void delete(Integer integer) {
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_delete_customer(?)");
            cs.setInt(1, integer);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(con);
        }
    }
}
