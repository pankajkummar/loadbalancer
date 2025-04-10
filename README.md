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

Step 3: start loadbalancer using 
        
    sh bash_scripts/start_loadbalancer/start-loadbalancer.sh
<br> - loadbalacer will start on port 8080

Step 4: start dummy servers using 

Note: If you want to change the Port or add additional server can be done through properties file in bash_script folder.

    sh bash_scripts/dummy-servers/run_dummyServers.sh
<br> - You will have 3 dummy server running on port 8081, 8082, 8083
# Steps to Test the functionality of loadbalancer

### 1. List server ( /admin/servers )
Use postman or web browser and send get request to http://localhost:8080/admin/servers
<br>Json response : list of registered servers

<pre lang="json">

[
  {
    "url": "http://localhost:8081",
    "healthy": true
  },
  {
    "url": "http://localhost:8082",
    "healthy": true
  },
  {
    "url": "http://localhost:8083",
    "healthy": true
  },
  {
    "url": "http://localhost:8084",
    "healthy": false
  }
]

</pre>


### 2. Add server ( /admin/add )
use postman and send post request with server url to http://localhost:8080/admin/add
<br>
<br>Json body :
<pre>

<code>
{
  "url": "http://localhost:8084"
}
</code>

</pre>

### 3. Remove server ( /admin/remove )
use postman and send delete request with server url to http://localhost:8080/admin/remove  
<br>Json body :

<pre>

<code>
{
  "url": "http://localhost:8084"
}
</code>

</pre>
### 4. change Strategy  ( /admin/strategy)
use postman and send post request with server url to http://localhost:8080/admin/strategy
<br>Json body :
<pre>

<code>
{
  "name":"random"
}
</code>

</pre>

<br>
Use "random" to choose random strategy and "round-robin" to chose round-robin strategy. these two strategies are implemented.
<br> round-robin is the default strategy.

### 5. check route forwarding by loadbalancer
use any browser or postman and send a get request to http://localhost:8080
<br>
Response : You reached Server-1 on port 8081
<br>
If you hit same url multiple time you will notice server name and port is changing