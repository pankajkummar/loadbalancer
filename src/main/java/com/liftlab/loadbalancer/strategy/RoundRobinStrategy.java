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
        int i = Math.abs(index.getAndIncrement() % servers.size());
        return servers.get(i);
    }

    @Override
    public String getName() {
        return "round-robin";
    }

}
