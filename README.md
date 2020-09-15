# Microservice with Kafka and Resilience4j using Spring Boot 2 demo

This demo shows how a Spring Boot 2 microservice consumes from a Kafka topic and uses the fault tolerance library [Resilience4j](https://github.com/resilience4j/resilience4j) to isolate from fails in mongoDb database.

See [Resilience4j User Guide](https://resilience4j.readme.io/docs/getting-started-3) for more details. 

## Getting Started

To run the complete Demo application, just run the 01-start.sh bash script.
```
./01-start.sh
```
This script will:
 - Start the docker machine on your local machine, 
 - Build the application image
 - Start the docker images
 - Display the application logs

To stop the Demo application, just run:
```
./02-stop.sh
```

## Monitoring with Prometheus and Grafana

The provided docker-compose file will deploy the following things in your local machine:
 - [Swagger documentation](http://192.168.99.102:8081/swagger-ui.html#) page
 - [Prometheus](http://192.168.99.102:9090) methics UI
 - [Grafana](http://192.168.99.102:3000) with Resilience4j Dashboard. (User: admin, Password: root)  
  
### Requirements
[Docker](https://docs.docker.com/install/) and [Docker Compose](https://docs.docker.com/compose/install/) installed.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/gradle-plugin/reference/html/#build-image)
* [Resilience4J](https://cloud.spring.io/spring-cloud-static/spring-cloud-circuitbreaker/current/reference/html)
* [Apache Kafka Streams Support](https://docs.spring.io/spring-kafka/docs/current/reference/html/_reference.html#kafka-streams)
* [Apache Kafka Streams Binding Capabilities of Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/#_kafka_streams_binding_capabilities_of_spring_cloud_stream)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Samples for using Apache Kafka Streams with Spring Cloud stream](https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/kafka-streams-samples)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

 