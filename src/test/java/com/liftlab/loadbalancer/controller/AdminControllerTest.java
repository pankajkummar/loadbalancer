package com.liftlab.loadbalancer.controller;

import com.liftlab.loadbalancer.service.LoadBalancerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoadBalancerService service;

    @Test
    void testAddServer() throws Exception {
        mockMvc.perform(post("/admin/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\": \"http://localhost:8081\"}"))
                        .andExpect(status().isOk())
                        .andExpect(content().string("Server added http://localhost:8081"));
    }
    @Test
    void testRemoveServer() throws Exception {
        mockMvc.perform(delete("/admin/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\": \"http://localhost:8081\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Server removed : http://localhost:8081"));
    }

    @Test
    void testSwitchStrategy() throws Exception {
        mockMvc.perform(post("/admin/strategy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"random\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Switched to strategy: random"));
    }
}
