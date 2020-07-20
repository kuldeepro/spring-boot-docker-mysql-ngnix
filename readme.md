# Spring Boot | Docker | Mysql | Nginx
Application runs three docker containers for Spring boot, Mysql & Nginx. Mysql container runs independent of other two containers. Spring boot container waits for mysql container to start accepting connection before starting and then open the service request using nginx at port 80. 

Note: I would not advise to run mysql in Dockerized container on production 

## Prerequisites

   - Install Docker
   - Install Docker Compose
   - Install JDK1.8 to build spring boot application 

## How to run it

Assume you already have Docker & Docker compose installed. See https://docs.docker.com/installation/. Create a docker network so all the containers can connect to each other. Finde below the command for creating a new network.

Next, clone the project and run shell script start.sh:

~~~
docker nertwork create tenet-network

git clone https://github.com/kuldeepro/spring-boot-docker-mysql-ngnix.git

cd spring-boot-docker-mysql-ngnix

./start.sh


~~~

Test webservices using following:

Note: Pass a "token" in header with value "tenet" for authorization.
~~~
localhost:80/app/get-db-details
localhost:80/app/ping
~~~

Shell script will start mysql container and will wait until mysql container is ready. Once mysql container is ready it will build Spring Boot application and start a new app container to run it.

To stop and kill all the running containers use:

~~~
./stop.sh
~~~