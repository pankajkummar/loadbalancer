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
    private List<String> excludePaths;
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

    public List<String> getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = excludePaths;
    }
}
