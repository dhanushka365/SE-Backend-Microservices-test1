



#docker compose -d up
# Mid Evaluation Presentation
[School Management System.pdf](https://github.com/dhanushka365/SE-Backend-Microservices-test1/files/12435947/School.Management.System.pdf)


# docker logs zipkin!
[API ENDPOINTS](https://github.com/dhanushka365/SE-Backend-Microservices-test1/assets/66137046/df1103bd-3f26-4517-8b54-e0429e6e59ea)

* ##### Jar files created without any errors and using following commands we can execute them.Please execute those command in the root directory.
1. [x] java -jar student/target/student-0.0.1-SNAPSHOT.jar
2. [x] java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar
3. [x] java -jar fraud/target/fraud-0.0.1-SNAPSHOT.jar
4. [x] java -jar notification/target/notification-0.0.1-SNAPSHOT.jar
5. [x] java -jar api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar
6. [x] java -jar amqp/target/amqp-0.0.1-SNAPSHOT.jar

mvn clean package -P build-docker-image
docker compose pull
docker compose up -d
docker ps --format=$FORMAT

#Check Docker Logs
docker logs eureka-server
docker logs student
docker logs fraud
docker logs notification

docker compose stop
docker compose start
docker compose down

docker pull eduetor/course:latest
docker pull eduetor/teacher:latest
docker pull eduetor/api-gateway:latest
docker pull eduetor/notification:latest
docker pull eduetor/eureka-server:latest
docker pull eduetor/fraud:latest
docker pull eduetor/student:latest
docker pull eduetor/course:latest



