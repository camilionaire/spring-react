package com.camilo.lil.wisdompet.web.rest;

import com.camilo.lil.wisdompet.services.ProductService;
import com.camilo.lil.wisdompet.web.errors.BadRequestException;
import com.camilo.lil.wisdompet.web.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        return this.productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return this.productService.createOrUpdate(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id")long id) {
        return this.productService.getProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id")long id,
                                 @RequestBody Product product) {
        if (id != product.getProductId()) {
            throw new BadRequestException("ids do not match");
        }
        return this.productService.createOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteProduct(@PathVariable("id")long id) {
        this.productService.deleteProduct(id);
    }
}
