package me.gladysz.services.cinema.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("cinema-service")
@RequestMapping("/api/cinema")
public interface CinemaServiceClient {

}
