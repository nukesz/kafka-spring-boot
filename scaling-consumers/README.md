# Scaling Consumers
## Understanding Partitions and Consumer Groups

Kafka is designed for high-throughput messaging, but how does it efficiently handle large volumes of data? A **single consumer** processing all messages can quickly become a bottleneck. We need a way to **scale message consumption across multiple consumers**.

### Solution: Partitioning and Consumer Groups

Kafka solves the scalability problem with **partitions** and **consumer groups**:
- Partitions: Kafka splits a topic into partitions, distributing messages among them.
- Consumer Groups: A set of consumers that share the workload. Each partition is assigned to **only one consumer per group**. 

How Partitions Work:
- When a producer sends a message, Kafka uses a partitioning strategy to decide which partition to store it in.
- Consumers in the same group divide the partitions among themselves.
- If a consumer fails, Kafka re-balances and reassigns the partition to another consumer.

### Example: Processing Stock Prices

In this chapter, we simulate real-time stock price updates.

- A producer will send 10,000 random stock price updates (e.g., `AAPL: $189.45`, `GOOGL: $2725.20`).
- Each stock ticker (e.g., `AAPL`) is assigned to a specific partition, ensuring order preservation within each stock.
- Multiple consumers will process stock prices in parallel.

Goal:
- Observe how Kafka routes messages to partitions.
- Scale consumers dynamically and see the rebalancing effect.

```
             ┌──────────────┐
             │   Producer   │
             └──────┬───────┘
                    │
   ┌────────────────┴──────────────────┐
   │         Kafka Topic: stocks       │
   └───┬────────────┬────────────┬─────┘
     Part 0       Part 1       Part 2
     (AAPL)      (GOOGL)      (MSFT)
       │            │            │
  ┌────┴────┐  ┌────┴────┐  ┌────┴────┐
  │ Consumer│  │ Consumer│  │ Consumer│
  │   #1    │  │   #2    │  │   #3    │
  └─────────┘  └─────────┘  └─────────┘
```

- `AAPL` messages always go to the same partition (ordering guaranteed).
- Consumers share the workload across partitions.
- If **Consumer #2 fails**, Kafka will **re-balance** and assign `GOOGL`’s partition to another consumer.
