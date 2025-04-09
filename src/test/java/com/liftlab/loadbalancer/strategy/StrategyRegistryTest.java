package com.liftlab.loadbalancer.strategy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StrategyRegistryTest {
    @Mock
    RoundRobinStrategy roundRobinStrategy;

    @Mock
    RoundRobinStrategy randomStrategy;

    @Test
    void shouldSetStrategyToRoundRobinByDefault(){
        Mockito.when(roundRobinStrategy.getName()).thenReturn("round-robin");
        Mockito.when(randomStrategy.getName()).thenReturn("random");
        StrategyRegistry registry = new StrategyRegistry(List.of(roundRobinStrategy,randomStrategy));
        assertEquals(roundRobinStrategy,registry.getCurrentStrategy());
    }
    @Test
    void shouldSwitchToRandomStrategy(){
        Mockito.when(roundRobinStrategy.getName()).thenReturn("round-robin");
        Mockito.when(randomStrategy.getName()).thenReturn("random");

        StrategyRegistry registry = new StrategyRegistry(List.of(roundRobinStrategy,randomStrategy));
        registry.setStrategy("random");
        assertEquals(randomStrategy,registry.getCurrentStrategy());
    }
}
