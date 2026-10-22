package com.example.demo.dto;

import java.util.List;

public record CameraInfoDto(
        int id,
        String device_type,
        String status,
        List<String> features,
        Location location
) {
    public record Location(
            String room,
            int floor
    ) {}
}
