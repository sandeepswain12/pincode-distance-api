package com.pincodedistanceapi.service.impl;

import com.pincodedistanceapi.dto.DistanceResponse;
import com.pincodedistanceapi.entity.Route;
import com.pincodedistanceapi.repository.RouteRepository;
import com.pincodedistanceapi.service.DistanceService;
import com.pincodedistanceapi.service.GoogleMapsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistanceServiceImpl implements DistanceService {

    private final GoogleMapsService mapsService;
    private final RouteRepository routeRepository;

    Logger logger = LoggerFactory.getLogger(DistanceServiceImpl.class);

    public DistanceResponse getDistance(String from, String to) {
        logger.info("Fetching distance between {} and {}", from, to);

        return routeRepository.findByFromPincodeAndToPincode(from, to)
                .map(route -> {
                    logger.info("Cache hit: returning saved route from DB");
                    return new DistanceResponse(from, to, route.getDistanceText(), route.getDurationText(), route.getRouteDetails());
                })
                .orElseGet(() -> {
                    logger.info("Cache miss: calling Google Maps API");
                    DistanceResponse response = mapsService.getDistance(from, to);

                    Route route = Route.builder()
                            .fromPincode(from)
                            .toPincode(to)
                            .distanceText(response.getDistance())
                            .durationText(response.getDuration())
                            .distanceValue(0)
                            .durationValue(0)
                            .routeDetails("")
                            .build();

                    routeRepository.save(route);
                    logger.info("Saved new route to DB");
                    return response;
                });
    }
}
