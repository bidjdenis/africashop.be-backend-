FROM openjdk:21
VOLUME /tmp
EXPOSE 8080
COPY target/*.jar africashop.jar
ENTRYPOINT ["java","-jar","africashop.jar"]