package com.pincodedistanceapi;

import com.pincodedistanceapi.dto.DistanceResponse;
import com.pincodedistanceapi.service.impl.DistanceServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PincodeDistanceApiApplicationTests {

	@Autowired
	private DistanceServiceImpl distanceServiceImpl;

	@Test
	public void testDistanceCalculation() {
		DistanceResponse response = distanceServiceImpl.getDistance("141106", "110060");

		assertNotNull(response);
		assertEquals("141106", response.getFrom());
		assertEquals("110060", response.getTo());
		assertNotNull(response.getDistance());
		assertNotNull(response.getDuration());
		assertNotNull(response.getRouteDetails());
	}

}
