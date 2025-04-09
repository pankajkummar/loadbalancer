package com.liftlab.loadbalancer.model;


public class ServerModel {
    private String url;
    private boolean healthy=true;

    public ServerModel() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ServerModel(String url) {
        this.url = url;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }
}
