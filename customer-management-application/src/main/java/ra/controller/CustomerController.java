package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.dao.CustomerDAOImpl;
import ra.model.dao.ICustomerDAO;
import ra.model.entity.Customer;
import ra.model.service.ICustomerService;

import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("/getAll")
    public String showList(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("cus", customers);
        return "customer/list";
    }

    @GetMapping("/create")
    public String formCreateCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute("customer") Customer customer) {
        boolean check = customerService.save(customer);
        if (check) {
            return "redirect:getAll";
        } else {
            return "redirect:create";
        }
    }

    @GetMapping("/edit")
    public String formEditCustomer(Model model,@RequestParam("idEdit") int idEdit) {
        Customer customer = customerService.findById(idEdit);
        model.addAttribute("cusEdit", customer);
        return "customer/edit";
    }

    @PostMapping("/edit")
    public String editCustomer(@ModelAttribute("cusEdit") Customer customer){
        boolean check = customerService.save(customer);
        if (check) {
            return "redirect:getAll";
        } else {
            return "redirect:edit";
        }
    }
    @GetMapping("/delete")
    public  String deleteCustomer(int idDel){
        customerService.delete(idDel);
        return "redirect:getAll";
    }
}
