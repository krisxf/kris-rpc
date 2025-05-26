package com.kris.nettyclient.vo;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-05-11 14:41
 **/
public class ServiceInfo {
    private Integer id;
    private String serviceName;
    private String address;

    public ServiceInfo() {}

    public ServiceInfo(Integer id, String serviceName, String address) {
        this.id = id;
        this.serviceName = serviceName;
        this.address = address;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
