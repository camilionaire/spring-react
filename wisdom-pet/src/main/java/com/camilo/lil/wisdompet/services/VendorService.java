package com.camilo.lil.wisdompet.services;

import com.camilo.lil.wisdompet.data.entities.VendorEntity;
import com.camilo.lil.wisdompet.data.repositories.VendorRepository;
import com.camilo.lil.wisdompet.web.errors.NotFoundException;
import com.camilo.lil.wisdompet.web.models.Vendor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> getAllVendors() {
        Iterable<VendorEntity> entities = this.vendorRepository.findAll();
        List<Vendor> vendors = new ArrayList<>();
        entities.forEach(entity -> {
            vendors.add(this.translateDbToWeb(entity));
        });
        return vendors;
    }

    public Vendor getVendor(long id) {
        Optional<VendorEntity> optional = this.vendorRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("vendor not found with id");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Vendor createOrUpdate(Vendor vendor) {
        VendorEntity entity = this.translateWebToDb(vendor);
        entity = this.vendorRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteVendor(long id) {
        this.vendorRepository.deleteById(id);
    }
    private VendorEntity translateWebToDb(Vendor vendor) {
        VendorEntity entity = new VendorEntity();
        entity.setId(vendor.getVendorId());
        entity.setName(vendor.getName());
        entity.setContact(vendor.getContact());
        entity.setEmail(vendor.getEmailAddress());
        entity.setPhone(vendor.getPhoneNumber());
        entity.setAddress(vendor.getAddress());
        return entity;
    }

    private Vendor translateDbToWeb(VendorEntity entity) {
        return new Vendor(entity.getId(), entity.getName(), entity.getContact(),
                entity.getPhone(), entity.getEmail(), entity.getAddress());
    }
}
