package com.ProjectSD.project.Services.Implementation;

import com.ProjectSD.project.Entities.Device;
import com.ProjectSD.project.Entities.User;
import com.ProjectSD.project.Repositories.DeviceRepository;
import com.ProjectSD.project.Services.DeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImplementation implements DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceServiceImplementation(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> findAll() {
        List<Device> devices = new ArrayList<>();
        deviceRepository.findAll().forEach(devices::add);
        return devices;
    }

    @Transactional
    @Override
    public Device findById(long id) {
        return deviceRepository.findFirstById(id);
    }

    @Transactional
    @Override
    public Device updateDevice(Device device) {
        Device deviceDb = deviceRepository.findById(device.getId()).get();
        deviceDb.setAddress(device.getAddress());
        deviceDb.setDescription(device.getDescription());
        deviceDb.setMaximumHourlyEnergyConsumption(device.getMaximumHourlyEnergyConsumption());

        return deviceDb;
    }

    @Transactional
    @Override
    public Device createDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Transactional
    @Override
    public Device deleteDevice(Device device) {
       deviceRepository.delete(device);

       return device;
    }
}
