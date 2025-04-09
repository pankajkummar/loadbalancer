package com.liftlab.loadbalancer.controller;

import com.liftlab.loadbalancer.service.LoadBalancerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Enumeration;

@RestController
@RequiredArgsConstructor
public class LoadBalancerController {
    @Autowired
    private LoadBalancerService loadBalancerService;




    @RequestMapping("/loadbalancer/**")
    public ResponseEntity<?> handleRequest(HttpMethod method, HttpServletRequest request, @RequestBody(required = false) String body) throws IOException
    {
        String path = request.getRequestURI();
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String h = headerNames.nextElement();
            headers.add(h, request.getHeader(h));
        }
            return loadBalancerService.forwardRequest(method, body, headers, path);

    }

}
