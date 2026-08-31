package cs.yogesh.foodstuffs_api.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.ArrayList;
import java.util.List;
import cs.yogesh.foodstuffs_api.model.Product;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ProductController {
    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "Milk", 4.50, "Dairy", 20));
        products.add(new Product(2, "Bread", 3.00, "Bakery", 15));
        products.add(new Product(3, "Rice", 8.50, "Grains", 30));
        products.add(new Product(4, "Apples", 5.00, "Fruit", 25));
        products.add(new Product(5, "Chicken", 12.00, "Meat", 10));
    }
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/products/search")
    public List<Product> searchProducts(@RequestParam String name) {

        List<Product> results = new ArrayList<>();

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(product);
            }
        }

        return results;
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {

        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {

        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setCategory(updatedProduct.getCategory());
                product.setStock(updatedProduct.getStock());

                return product;
            }
        }


        return null;

    }
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {

        products.removeIf(product -> product.getId() == id);

    }

}

