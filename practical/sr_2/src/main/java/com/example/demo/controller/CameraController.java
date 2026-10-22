package com.example.demo.controller;

import com.example.demo.dto.CameraInfoDto;
import com.example.demo.dto.SnapshotDto;
import com.example.demo.entity.CameraEntity;
import com.example.demo.service.CameraService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;

@Tag(name = "Camera")
@RestController
@RequestMapping("/api/v1/camera")
@RequiredArgsConstructor
public class CameraController {

    private final CameraService cameraService;

    @GetMapping("/info")
    public CameraInfoDto getCameraInfo() {
        return cameraService.getCameraInfo();
    }

    @PostMapping(
            value = "/snapshot",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> captureSnapshot() {
        SnapshotDto snapshot = cameraService.captureSnapshot();
        byte[] image = Base64.getDecoder().decode(snapshot.image());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    @PostMapping("/import")
    public List<CameraEntity> importCameras() {
        return cameraService.importCameras();
    }

    @GetMapping("/saved")
    public List<CameraEntity> getSavedCameras() {
        return cameraService.getSavedCameras();
    }

    @GetMapping("/saved/{id}")
    public CameraEntity getSavedCamera(@PathVariable Long id) {
        return cameraService.getSavedCamera(id);
    }

    @DeleteMapping("/saved/{id}")
    public ResponseEntity<Void> deleteSavedCamera(@PathVariable Long id) {
        cameraService.deleteSavedCamera(id);
        return ResponseEntity.noContent().build();
    }
}
