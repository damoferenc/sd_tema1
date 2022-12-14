package com.ProjectSD.project.Services;

import com.ProjectSD.project.Entities.Device;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceService {
    List<Device> findAll();
    Device findById(long id);
    Device createDevice(Device device);
    Device updateDevice(Device device);
    Device deleteDevice(Device device);
}
