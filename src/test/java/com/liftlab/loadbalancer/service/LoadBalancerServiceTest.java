package com.liftlab.loadbalancer.service;

import com.liftlab.loadbalancer.config.LoadBalancerConfig;
import com.liftlab.loadbalancer.model.ServerModel;
import com.liftlab.loadbalancer.strategy.LoadBalancerStrategy;
import com.liftlab.loadbalancer.strategy.StrategyRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoadBalancerServiceTest {
    @Mock
    private LoadBalancerConfig config;

    @Mock
    private StrategyRegistry strategyRegistry;

    @Mock
    private LoadBalancerStrategy strategy;

    @InjectMocks
    private LoadBalancerService service;

    @Test
    void shouldAddAndRemoveServers(){
        ServerModel server = new ServerModel("http://localhost:8081");
        service.addServer(server);
        assertTrue(service.getServers().contains(server));

        service.removeServer(server.getUrl());
        assertFalse(service.getServers().contains(server));
    }
    @Test
    void shouldSetStrategy() {
        service.setStrategy("random");
        verify(strategyRegistry).setStrategy("random");

    }
}
