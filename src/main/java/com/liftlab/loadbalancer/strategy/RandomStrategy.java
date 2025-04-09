package com.liftlab.loadbalancer.strategy;

import com.liftlab.loadbalancer.model.ServerModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomStrategy implements LoadBalancerStrategy {
    private final Random random = new Random();
    @Override
    public ServerModel selectServer(List<ServerModel> servers) {
        if(servers.isEmpty()) return null;
        List<ServerModel> healthyServers = servers.stream()
                .filter(ServerModel::isHealthy)
                .toList();
        return healthyServers.get(random.nextInt(healthyServers.size()));
    }

    @Override
    public String getName() {
        return "random";
    }
}
