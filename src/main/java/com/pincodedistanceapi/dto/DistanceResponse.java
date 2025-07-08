package com.pincodedistanceapi.dto;

import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DistanceResponse {
    private String from;
    private String to;
    private String distance;
    private String duration;
    private String routeDetails;
}
