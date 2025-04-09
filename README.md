**Liftlab Assignment Loadbalancer**

## Overview
This is a spring-boot based Load Balancer that forwards the incoming HTTP requests to registered backend servers using strateg (Round Robin, Random, etc.). It includes admin endpoints to manage servers and strategies dynamically, and supports basic heath checks for the server before forwarding the request. Scripts are added int repository to start dummy servers and the loadbalancer.

# Technology Used
Java 21, Spring-boot

# High Level Diagram LoadBalancer
![Load Balancer HLD](images/loadbalancerHLD.png)

# Class diagram of Load Balancer
![Load Balancer LLD](images/loadBalancerLLD.png)

# How to run the application

Step 1: Clone the project repository.

Step 2: Go to directory bash_scripts

Step 3: start loadbalancer using sh bash_scripts/start_loadbalancer/start-loadbalancer.sh

Step 4: start dummy servers using sh bash_scripts/dummy-servers/run_dummyServers.sh

# Steps to Test the functionality of loadbalancer


