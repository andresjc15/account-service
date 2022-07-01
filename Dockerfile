FROM openjdk:11
VOLUME /tmp
ADD ./target/account-service-1.0.0.jar account-service-1.0.0.jar
ENTRYPOINT ["java","-jar","/account-service-1.0.0.jar"]