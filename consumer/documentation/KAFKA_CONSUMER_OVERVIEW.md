## Kafka Consumer Overview

# What is a Kafka Consumer?
A Kafka Consumer is an application or service that subscribes to Kafka topics and processes the messages published by producers.
Consumers read messages from Kafka topics, and they can be part of a consumer group to enable load balancing and fault tolerance. 
Each consumer in a group reads messages from a subset of the partitions of the topic, 
ensuring that messages are processed by only one consumer in the group.

---

# Kafka Producer-Consumer Architecture
Kafka Producer interacts (in a separate application) with Kafka Broker and how a Consumer (in a separate application) receives messages from Kafka.
No direct relationship between producer and consumer, they are decoupled and communicate through Kafka Broker.

    ┌─────────────────────────────┐
    │    Producer Application     │ (sends messages)
    │         Port: 8081          │
    │                             │
    │ ┌─────────────────────────┐ │
    │ │ REST API Client         │ │ (HTTP Request)
    │ │ POST /kafka/add-course  │ │
    │ └────────────┬────────────┘ │
    │              │ receives     │
    │              ▼              │
    │ ┌─────────────────────────┐ │
    │ │ KafkaController         │ │ (receives requests)
    │ │ @PostMapping            │ │
    │ └───────────┬─────────────┘ │
    │             │ sends Course  │
    │             ▼               │
    │ ┌─────────────────────────┐ │
    │ │ KafkaService            │ │ (sends messages)
    │ │ KafkaTemplate           │ │
    │ │ (JsonSerializer)        │ │
    │ └───────────┬─────────────┘ │
    └─────────────│───────────────┘
                  │ (JSON over Kafka)
                  ▼ 
    ┌─────────────────────────────┐
    │  Kafka Broker (9092)        │
    │  Topic: madhankafka         │
    └────────────┬────────────────┘
                 │ (JSON over Kafka)
                 ▼
    ┌─────────────────────────┐
    │   Consumer Application  │ (listens & stores)
    │      Port: 8082         │
    │                         │
    │ ┌─────────────────────┐ │
    │ │ KafkaService        │ │ (receives messages)
    │ │ @KafkaListener      │ │
    │ │ (JsonDeserializer)  │ │
    │ └────────┬────────────┘ │
    │          │ stores       │
    │          ▼              │
    │ ┌─────────────────────┐ │
    │ │ KafkaController     │ │ (exposes API)
    │ │ GET /kafka/..       │ │
    │ └─────────────────────┘ │
    └──────────┬──────────────┘
               │
               ▼
         REST API Client
       (Postman, cURL, App)

---

# How It Works?
- Producer: Sends messages to a Kafka topic (e.g., `madhankafka`).
- Kafka Broker: Stores messages in topics and manages message delivery between producers and consumers.
- Consumer: Listens/Reads messages from the topic (`madhankafka`) and not from Producer directly and stores message in memory.
- Group: Consumers organized in groups for offset management (`consumer-group`).
- REST API: returns the stored message when called.

---

# Consumer Deserialize Details:
- The consumer deserializes data received from a Kafka topic in Kafka Broker.
- Each message is received from a topic (e.g., `madhankafka`).
- Multiple consumers in a consumer group can receive messages from the same topic, with each consumer processing a subset of partitions.
- Deserialization converts JSON messages back to Course objects for processing.

# Topic Management (Consumer Side):
- The consumer must know the topic name from which it receives messages (`madhankafka`).
- Consumers need to manage offset/position tracking for fault tolerance and resuming from where they left off.
- Consumers organized in consumer groups enable load balancing and ensure each message is processed by only one consumer in the group.
- The consumer group handles automatic partition assignment and rebalancing when consumers join or leave.

---

# Key Components:

# Consumer Side:
| Component | Purpose |
|-----------|---------|
| KafkaService | Listens to Kafka topic, stores messages |
| KafkaController | REST API endpoint to retrieve messages |
| Course Model | Data structure for course information |
| JsonDeserializer | Deserializes JSON messages back to Course objects |
| application.yaml | Configuration for Kafka connection & deserialization |

---
# Note:
    This project implements a simple Kafka consumer in a separate application
    that receives course information from a Kafka topic (madhankafka).

---