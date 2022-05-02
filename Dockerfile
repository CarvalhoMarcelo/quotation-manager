FROM openjdk:8-jdk-alpine
LABEL maintainer="silva.marcelocarvalho@gmail.com"
ADD target/quotation-manager.jar quotation-manager.jar
ENTRYPOINT ["java","-jar","quotation-manager.jar"]
