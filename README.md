## Build instructions

The project is built with Maven 3 and Java 8, so you need JDK >=1.8 and a reasonable version of maven installed on your computer.


## Docker build image instructions

To create a docker image you need a docker installation in your machine

### Command to create a docker image (locally)

`` mvn clean install docker:build``

## Infrastructure Requisites

You need a rabbitmq up and running to up the service, we recommend using docker container

The following command can help you to spin up a fresh rabbitmq container

``docker run -d --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3.7.0-management-alpine``
