package com.pincodedistanceapi.service.impl;

import com.pincodedistanceapi.dto.DistanceResponse;
import com.pincodedistanceapi.exception.InvalidPincodeException;
import com.pincodedistanceapi.service.GoogleMapsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.*;

@Service
@RequiredArgsConstructor
public class GoogleMapsServiceImpl implements GoogleMapsService {

    private final RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(GoogleMapsServiceImpl.class);

    @Value("${google.maps.api.key}")
    private String apiKey;

    @Cacheable(value = "distanceCache", key = "#from + '-' + #to")
    public DistanceResponse getDistance(String from, String to) {
        logger.info("Calling Google Maps API for {} → {}", from, to);
        try {
            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + from +
                    "&destinations=" + to + "&key=" + apiKey;

            String response = restTemplate.getForObject(url, String.class);
            logger.debug("Google API response: {}", response);

            JSONObject json = new JSONObject(response);
            JSONObject element = json.getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0);

            if (!element.has("distance") || element.has("status") && element.getString("status").equals("ZERO_RESULTS")) {
                logger.warn("Invalid route or ZERO_RESULTS for {} → {}", from, to);
                throw new InvalidPincodeException("No route found between " + from + " and " + to);
            }

            String distanceText = element.getJSONObject("distance").getString("text");
            long distanceValue = element.getJSONObject("distance").getLong("value");

            String durationText = element.getJSONObject("duration").getString("text");
            long durationValue = element.getJSONObject("duration").getLong("value");

            logger.info("Received distance: {} ({}), duration: {} ({})", distanceText, distanceValue, durationText, durationValue);

            return new DistanceResponse(from, to, distanceText, durationText, response);

        } catch (Exception e) {
            logger.error("Error while calling Google Maps API: {}", e.getMessage());
            throw new InvalidPincodeException("Google API call failed: " + e.getMessage());
        }
    }
}

