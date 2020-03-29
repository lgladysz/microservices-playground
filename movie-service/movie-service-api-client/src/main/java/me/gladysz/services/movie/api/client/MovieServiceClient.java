package me.gladysz.services.movie.api.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("movie-service")
@RequestMapping("/api/movie")
public interface MovieServiceClient {

}
