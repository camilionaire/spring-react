package com.camilo.lil.wisdompet.data.repositories;

import com.camilo.lil.wisdompet.data.entities.ServiceEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<ServiceEntity, Long> {
}
