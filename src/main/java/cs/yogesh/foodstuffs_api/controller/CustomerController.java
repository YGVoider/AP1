package cs.yogesh.foodstuffs_api.controller;
import org.springframework.web.bind.annotation.GetMapping;
import cs.yogesh.foodstuffs_api.model.Customer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private static List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer(1, "John Smith", "john@email.com", "0211111111", "Active"));
        customers.add(new Customer(2, "Sarah Lee", "sarah@email.com", "0222222222", "Active"));
        customers.add(new Customer(3, "Mike Brown", "mike@email.com", "0273333333", "Inactive"));
    }
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customers;
    }
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable int id) {

        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }

        return null;
    }
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }
    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {

        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customer.setName(updatedCustomer.getName());
                customer.setEmail(updatedCustomer.getEmail());
                customer.setPhone(updatedCustomer.getPhone());
                customer.setStatus(updatedCustomer.getStatus());

                return customer;
            }
        }

        return null;
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customers.removeIf(customer -> customer.getId() == id);
    }

}