package com.camilo.lil.wisdompet.data.repositories;

import com.camilo.lil.wisdompet.data.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    ProductEntity findByEmail(String email);
}
