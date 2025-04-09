package com.liftlab.loadbalancer.model;


public class ServerModel {
    private String url;

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
}
