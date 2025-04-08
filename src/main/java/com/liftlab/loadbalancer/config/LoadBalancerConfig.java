package com.liftlab.loadbalancer.config;

import com.liftlab.loadbalancer.model.ServerModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "loadbalancer")
public class LoadBalancerConfig {
    private List<ServerModel> servers;
    private String strategy = "round-robin";

    public List<ServerModel> getServers(){
        return servers;
    }
    public void setServers(List<ServerModel> servers) {
        this.servers = servers;
    }
    public String getStrategy() {
        return strategy;
    }
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

}
