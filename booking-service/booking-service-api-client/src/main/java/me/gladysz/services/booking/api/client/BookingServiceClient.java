package me.gladysz.services.booking.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("booking-service")
@RequestMapping("/api/booking")
public interface BookingServiceClient {

}
