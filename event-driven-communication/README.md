# Event Driver Communication

This is our "Hello Kafka" example where we learn more about the basic topics in kafka. I tried to document and describe the most interesting classes and methods, so please start in the [kafka](src/main/java/com/github/nukesz/kafka) folder.

```sh
# Start kafka
docker-compose up -d

# Run the spring-boot application
../gradlew :event-driven-communication:bootRun

# Send a message
curl -XPOST "http://localhost:8080/kafka/send?message=HelloKafka"

# Verify the message in the log
Received Message: HelloKafka

# Feel free to run multiple instances on different ports for load balancing
../gradlew :event-driven-communication:bootRun --args='--server.port=8081'
../gradlew :event-driven-communication:bootRun --args='--server.port=8082'
```
