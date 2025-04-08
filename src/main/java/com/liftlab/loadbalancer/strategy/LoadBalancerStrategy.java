package com.liftlab.loadbalancer.strategy;

import com.liftlab.loadbalancer.model.ServerModel;

import java.util.List;

public interface LoadBalancerStrategy {
    ServerModel selectServer(List<ServerModel> servers);
    String getName();
}
