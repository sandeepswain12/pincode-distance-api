package com.pincodedistanceapi.service;

import com.pincodedistanceapi.dto.DistanceResponse;

public interface GoogleMapsService {
    DistanceResponse getDistance(String from, String to);
}
