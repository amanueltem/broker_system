package com.coderscampus.BrokerSystem.dto;

import lombok.Data;

@Data
public class PlaceDto {
    private double area;
    private String address;
    private double price;
    private String imageUrl;
    private String status;
}
