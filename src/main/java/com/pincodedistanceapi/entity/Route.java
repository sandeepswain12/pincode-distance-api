package com.pincodedistanceapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromPincode;
    private String toPincode;

    private String distanceText;
    private String durationText;

    private long distanceValue;
    private long durationValue;

    @Lob
    private String routeDetails;
}
