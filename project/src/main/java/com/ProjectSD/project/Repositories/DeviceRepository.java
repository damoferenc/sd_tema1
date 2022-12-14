package com.ProjectSD.project.Repositories;

import com.ProjectSD.project.Entities.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
    Device findFirstById(long id);
}
