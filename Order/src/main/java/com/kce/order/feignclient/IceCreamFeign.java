package com.kce.order.feignclient;

import com.kce.order.model.IceCream;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ICECREAM-SERVICE")
public interface IceCreamFeign {
    @GetMapping("/api/icecream/get/{id}")
    public ResponseEntity<IceCream> findById(@PathVariable Long id);
    @GetMapping("/api/icecream/get/name/{name}")
    public ResponseEntity<IceCream> findByName(@PathVariable String name);

}
