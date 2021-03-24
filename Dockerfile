FROM openjdk:15 
ADD target/atm-app.jar atm-app.jar 
EXPOSE 8080 
ENTRYPOINT ["java","-jar","atm-app.jar"] 