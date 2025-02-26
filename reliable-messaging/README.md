# Reliable Messaging – Handling Errors and Retries

This chapter focuses on how to ensure reliable messaging using Kafka, including:
- Automatic retries for failed messages.
- Exponential backoff to avoid overwhelming Kafka.
- Dead-Letter Topic (DLT) handling to capture permanently failed messages.
- Sending message with a callback function to detect send failures.

## How to run
1. Start Kafka using Docker:
   ```sh
   docker-compose up -d
   ```
2. Start the application:
   ```sh
   ../gradlew :reliable-messaging:bootRun
   ```
3. Send a message
   ```sh
   curl -XPOST "http://localhost:8080/kafka/send?message=HelloKafka"
   ```
4. Verify the messages in the log
   ```sh
   Message sent successfully to topic: reliable-topic
   Received message: reliable-topic-0@56
   Received message: reliable-topic-retry-1000-0@5
   Received message: reliable-topic-retry-2000-0@5
   Received message: reliable-topic-retry-4000-0@5
   Received message: reliable-topic-retry-5000-0@5
   ...
   threw an error at topic reliable-topic-retry-5000 and won't be retried. Sending to DLT with name reliable-topic-dlt.
   ...
   Caused by: java.lang.RuntimeException: Something went wrong during message processing
   ...
   Dead-Letter Message received HelloKafka
   ```
