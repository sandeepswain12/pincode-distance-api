package com.pincodedistanceapi.controller;

import com.pincodedistanceapi.dto.DistanceRequest;
import com.pincodedistanceapi.dto.DistanceResponse;
import com.pincodedistanceapi.service.DistanceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/distance")
@RequiredArgsConstructor
public class DistanceController {

    Logger logger = LoggerFactory.getLogger(DistanceController.class);

    private final DistanceService distanceService;

    @PostMapping
    public ResponseEntity<DistanceResponse> getDistance(@RequestBody DistanceRequest request) {
        logger.info("POST /api/distance - Request: from={}, to={}", request.getFrom(), request.getTo());
        DistanceResponse res = distanceService.getDistance(request.getFrom(), request.getTo());
        return ResponseEntity.ok(res);
    }
}
