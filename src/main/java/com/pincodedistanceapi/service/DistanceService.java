package com.pincodedistanceapi.service;

import com.pincodedistanceapi.dto.DistanceResponse;

public interface DistanceService {
    DistanceResponse getDistance(String from, String to);
}
