package com.ProjectSD.project.Controllers;

import com.ProjectSD.project.Constants.Role;
import com.ProjectSD.project.DTO.DeviceDTO;
import com.ProjectSD.project.Entities.Device;
import com.ProjectSD.project.Entities.User;
import com.ProjectSD.project.Services.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
@CrossOrigin
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity findAllDevices(){
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.findAll());
    }

    @PostMapping
    public ResponseEntity createDevice(@RequestBody DeviceDTO dto){
        System.out.println(dto);
        Device device = Device.builder().description(dto.getDescription()).address(dto.getAddress())
                .maximumHourlyEnergyConsumption(dto.getMaximumHourlyEnergyConsumption()).build();
        System.out.println(device.getAddress());
        System.out.println(device.getId());
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.createDevice(device));
    }


    @PutMapping
    public ResponseEntity updateDevice(@RequestBody DeviceDTO dto){
        Device device = deviceService.findById(dto.getId());
        if(device == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Device not found");
        }
        device.setAddress(dto.getAddress());
        device.setDescription(dto.getDescription());
        device.setMaximumHourlyEnergyConsumption(dto.getMaximumHourlyEnergyConsumption());

        return ResponseEntity.status(HttpStatus.OK).body(deviceService.updateDevice(device));
    }


    @GetMapping("/{id}")
    public ResponseEntity findDeviceDetails(@PathVariable long id) {
        Device device = deviceService.findById(id);
        if(device == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Device not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(device);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDevice(@PathVariable Long id){
        Device device = deviceService.findById(id);
        if(device == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Device not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.deleteDevice(device));
    }


}
