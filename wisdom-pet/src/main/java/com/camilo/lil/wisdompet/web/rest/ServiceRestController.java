package com.camilo.lil.wisdompet.web.rest;

import com.camilo.lil.wisdompet.services.ServiceService;
import com.camilo.lil.wisdompet.web.errors.BadRequestException;
import com.camilo.lil.wisdompet.web.models.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceRestController {

    private final ServiceService serviceService;

    public ServiceRestController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<Service> getAll() {
        return this.serviceService.getAllServices();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Service createService(@RequestBody Service service) {
        return this.serviceService.createOrUpdateService(service);
    }

    @GetMapping("/{id}")
    public Service getService(@PathVariable("id")long id) {
        return this.serviceService.getService(id);
    }

    @PutMapping("/{id}")
    public Service updateService(@PathVariable("id")long id,
                                 @RequestBody Service service) {
        if (id != service.getServiceId()) {
            throw new BadRequestException("ids do not match");
        }
        return this.serviceService.createOrUpdateService(service);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteService(@PathVariable("id")long id) {
        this.serviceService.deleteService(id);
    }
}
