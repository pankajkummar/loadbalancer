package com.liftlab.loadbalancer.strategy;

import com.liftlab.loadbalancer.model.ServerModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RoundRobinStrategy implements  LoadBalancerStrategy {
    private final AtomicInteger index = new AtomicInteger(0);
    @Override
    public ServerModel selectServer(List<ServerModel> servers) {
        if(servers.isEmpty()) return null;
        List<ServerModel> healthyServers = servers.stream()
                .filter(ServerModel::isHealthy)
                .toList();

        if (healthyServers.isEmpty()) {
            throw new RuntimeException("No healthy servers available.");
        }
        int i = Math.abs(index.getAndIncrement() % servers.size());
        return healthyServers.get(i);
    }

    @Override
    public String getName() {
        return "round-robin";
    }

}
