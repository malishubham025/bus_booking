package com.busbooking.busbooking;

import com.busbooking.busbooking.dto.seatBookingDTO;
import com.busbooking.busbooking.repository.SeatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BusbookingApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private SeatRepository seatRepository;

	@Test
	void testConcurrentSeatBooking() throws InterruptedException {

//		// Prepare request body
//		seatBookingDTO request = new seatBookingDTO();
//		request.setId("SEAT_ID_HERE");   // put real seat id
//		request.setBus("BUS_ID_HERE");   // put real bus id
//
//		// Thread pool
//		ExecutorService executor = Executors.newFixedThreadPool(2);
//
//		CountDownLatch latch = new CountDownLatch(2);
//
//		List<ResponseEntity<String>> responses = Collections.synchronizedList(new ArrayList<>());
//
//		Runnable task = () -> {
//			try {
//				ResponseEntity<String> response = restTemplate.postForEntity(
//						"/book-seat",
//						request,
//						String.class
//				);
//				responses.add(response);
//			} finally {
//				latch.countDown();
//			}
//		};
//
//		// Fire both requests at same time
//		executor.submit(task);
//		executor.submit(task);
//
//		latch.await();
//		executor.shutdown();
//
//		// Print results
//		responses.forEach(res -> {
//			System.out.println("Response: " + res.getStatusCode());
//		});
//
//		// Assert: only one should succeed
//		long successCount = responses.stream()
//				.filter(r -> r.getStatusCode().is2xxSuccessful())
//				.count();
//
//		assertEquals(1, successCount);
	}
}
