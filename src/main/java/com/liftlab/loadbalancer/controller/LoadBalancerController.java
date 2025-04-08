package com.liftlab.loadbalancer.controller;

import com.liftlab.loadbalancer.service.LoadBalancerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class LoadBalancerController {
    private final LoadBalancerService loadBalancerService;

    @RequestMapping("/**")
    public ResponseEntity<?> handleRequest(HttpMethod method, HttpServletRequest request, @RequestBody(required = false) String body) throws IOException{
        String path = re
    }
}
