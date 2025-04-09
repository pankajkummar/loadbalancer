package com.liftlab.loadbalancer.service;

import com.liftlab.loadbalancer.model.ServerModel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class HealthCheckSchedulerService {
    private final LoadBalancerService service;
    private final RestTemplate restTemplate = new RestTemplate();

    public HealthCheckSchedulerService(LoadBalancerService service){
        this.service = service;
    }

    @Scheduled(fixedRate = 5000)
    public void checkHealth() {
        for(ServerModel server: service.getServers()) {
            try {
                restTemplate.getForEntity(server.getUrl() + "/health", String.class);
                server.setHealthy(true);
            }
            catch (Exception e) {
                server.setHealthy(false);
            }
        }
    }
}
