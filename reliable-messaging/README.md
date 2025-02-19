# Reliable Messaging – Handling Message Acknowledgments and Retries

This chapter focuses on ensuring reliable message delivery in Kafka by handling **acknowledgments** and **retries** effectively.

## Why this is important
- Kafka is designed to be highly reliable, but without proper handling, messages may be lost or processed multiple times.
- Handling acknowledgments properly ensures that messages are committed only when successfully processed.
- Retries help recover from temporary failures without losing data.

## What this chapter demonstrates
- How to configure Kafka **consumer acknowledgments** in Spring Boot.
- Implementing **manual commits** for fine-grained control over message processing.
- Handling **transient failures** using retry mechanisms.
- Using **dead-letter topics** to manage messages that continuously fail processing.

## What you will learn
- The difference between **automatic** and **manual** message acknowledgments.
- How to use **Kafka’s retry and error-handling mechanisms** to ensure reliable processing.
- Best practices for preventing **message loss and duplication**.

## How to run
1. Start Kafka using Docker:
   ```sh
   docker-compose up -d
   ```
2. Start the application:
   ```sh
   ../gradlew :reliable-messaging:bootRun
   ```
3. Observe logs to see how retries and manual acknowledgments are handled.

## Expected outcome
- Messages are acknowledged only after successful processing.
- If a transient failure occurs, the message is retried before being sent to a dead-letter topic.
- Manual acknowledgment provides fine-grained control over processing logic.

## Further reading
- [Retrying Messages in Kafka](https://www.confluent.io/blog/error-handling-patterns-in-kafka/)
