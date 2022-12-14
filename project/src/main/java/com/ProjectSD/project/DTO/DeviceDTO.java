package com.ProjectSD.project.DTO;

import lombok.Data;

@Data
public class DeviceDTO {

    private Long id;

    private String description;

    private String address;

    private Double maximumHourlyEnergyConsumption;

}
