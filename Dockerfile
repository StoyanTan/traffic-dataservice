FROM openjdk:18-alpine
MAINTAINER stv
COPY build/libs/traffic-dataservice-0.0.1-SNAPSHOT.jar traffic-dataservice-0.0.1.jar
ENTRYPOINT ["java","-jar","/traffic-dataservice-0.0.1.jar"]