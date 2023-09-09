package com.camilo.lil.wisdompet.services;

import com.camilo.lil.wisdompet.data.entities.CustomerEntity;
import com.camilo.lil.wisdompet.data.entities.ProductEntity;
import com.camilo.lil.wisdompet.data.repositories.ProductRepository;
import com.camilo.lil.wisdompet.web.errors.NotFoundException;
import com.camilo.lil.wisdompet.web.models.Customer;
import com.camilo.lil.wisdompet.web.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct(String filterEmail) {
        List<Product> products = new ArrayList<>();
        if (StringUtils.hasLength(filterEmail)) {
            ProductEntity entity = this.productRepository.findByEmail(filterEmail);
            products.add(this.translateDbToWeb(entity));
        } else {
            Iterable<ProductEntity> entities = this.productRepository.findAll();
            entities.forEach(entity -> {
                products.add(this.translateDbToWeb(entity));
            });
        }
        return products;
    }

    public Product getProduct(long id) {
        Optional<ProductEntity> optional = this.productRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("product not found with id");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Product createOrUpdate(Product product) {
        ProductEntity entity = this.translateWebToDb(product);
        entity = this.productRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    private ProductEntity translateWebToDb(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getProductId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setVendorId(product.getVendorId());
        return entity;
    }

    private Product translateDbToWeb(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getPrice(),
                entity.getVendorId());
    }

}
