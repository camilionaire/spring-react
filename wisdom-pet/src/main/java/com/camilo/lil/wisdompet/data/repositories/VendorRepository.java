package com.camilo.lil.wisdompet.data.repositories;

import com.camilo.lil.wisdompet.data.entities.VendorEntity;
import org.springframework.data.repository.CrudRepository;

public interface VendorRepository extends CrudRepository<VendorEntity, Long> {

    VendorEntity findByEmail(String email);
}
