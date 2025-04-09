package com.liftlab.loadbalancer.controller;

import com.liftlab.loadbalancer.model.ServerModel;
import com.liftlab.loadbalancer.service.LoadBalancerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private LoadBalancerService service;

    @PostMapping("/strategy")
    public String changeStrategy(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        service.setStrategy(name);
        return "Switched to strategy: " + name;
    }
    @PostMapping("/add")
    public String addServer(@RequestBody ServerModel server) {
        service.addServer(server);
        return "Server added " + server.getUrl();
    }
    @DeleteMapping("/remove")
    public String removeServer(@RequestBody ServerModel server){
        service.removeServer(server.getUrl());
        return "Server removed : "+ server.getUrl();
    }

    @GetMapping("/servers")
    public List<ServerModel> getServers(){
        return service.getServers();
    }
}
