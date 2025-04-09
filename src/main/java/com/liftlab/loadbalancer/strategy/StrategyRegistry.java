package com.liftlab.loadbalancer.strategy;

import com.liftlab.loadbalancer.service.LoadBalancerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StrategyRegistry {

    private static final Logger log = LoggerFactory.getLogger(StrategyRegistry.class);

    private final Map<String, LoadBalancerStrategy> strategyMap = new HashMap<>();
    private LoadBalancerStrategy currentStrategy;

    @Autowired
    public StrategyRegistry(List<LoadBalancerStrategy> strategies) {
        for(LoadBalancerStrategy strategy : strategies) {
            strategyMap.put(strategy.getName(), strategy);
        }
        this.currentStrategy = strategyMap.getOrDefault("round-robin", strategies.get(0));
    }
    public LoadBalancerStrategy getCurrentStrategy() {
        return currentStrategy;
    }

    public void setStrategy(String name){
        if(strategyMap.containsKey(name)) {
            currentStrategy = strategyMap.get(name);
            log.info("Current strategy set to: {}", name);
        }
        else {
            log.warn("Strategy '{}' not found. No changes made.", name);
        }
    }

}
