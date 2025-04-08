package com.liftlab.loadbalancer.service;

import com.liftlab.loadbalancer.config.LoadBalancerConfig;
import com.liftlab.loadbalancer.model.ServerModel;
import com.liftlab.loadbalancer.strategy.StrategyRegistry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.ErrorManager;
@Slf4j
@Service
public class LoadBalancerService {

    private static final Logger log = LoggerFactory.getLogger(LoadBalancerService.class);

    private final List<ServerModel> servers;
    private final StrategyRegistry strategyRegistry;
    private final RestTemplate restTemplate = new RestTemplate();

    public LoadBalancerService(LoadBalancerConfig config, StrategyRegistry strategyRegistry) {
        this.servers = config.getServers();
        this.strategyRegistry = strategyRegistry;
        strategyRegistry.setStrategy(config.getStrategy());
    }

    public ResponseEntity<?> forwardRequest(HttpMethod method, String body, HttpHeaders headers, String path){
        ServerModel selected = strategyRegistry.getCurrentStrategy().selectServer(servers);
        if (selected == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("No available servers");
        }
            String targetUrl = selected.getUrl() + path;
            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            try {
                return restTemplate.exchange(targetUrl,method,entity,String.class);
            } catch (Exception e) {
                log.error("Failed to forward to: "+ targetUrl, e);
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Server failed: "+ targetUrl);
            }
    }

    public void setStrategy(String name) {
        strategyRegistry.setStrategy(name);
    }
    public void addServer(ServerModel server) {
        servers.add(server);
    }
    public void removeServer(String url) {
        servers.removeIf(s -> s.getUrl().equals(url));
    }
    public List<ServerModel> getServers() {
        return servers;
    }

}



