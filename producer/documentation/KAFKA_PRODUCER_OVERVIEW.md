## Kafka Producer Overview

# What is a Kafka Producer?
A Kafka Producer is an application or service that sends (publishes) data (messages) to Kafka topics. 
Producers are responsible for creating and sending messages to Kafka clusters, 
which can then be consumed by other applications called consumers.

# Kafka Producer-Consumer Architecture
Kafka Producer (in a separate application) interacts with Kafka Broker and how a Consumer (in a separate application) receives messages from Kafka.
No direct relationship between producer and consumer, they are decoupled and communicate through Kafka Broker.

        +--------------+          +----------------------+          +--------------+
        |              |          |                      |          |              |
        |   PRODUCER   + -------> |     KAFKA BROKER     + -------> |   CONSUMER   |
        |              |          | (TOPIC: madhankafka) |          |              |
        +--------------+          +----------------------+          +--------------+
                                                            
---

# How It Works?
- Producer: Sends messages to a Kafka topic (e.g., `madhankafka`).
- Kafka Broker: Stores messages in topics and manages message delivery between producers and consumers.
- Consumer: Listens/Reads messages from the topic (not part of this project, it's separate application) and stores message in memory.
            The messages will be there in the queue until the consumer application is running and listening/getting to the topic.
- REST API: returns the stored message when called.

# Producer Serialize Details:
- The producer serializes data and sends it to a specific topic in Kafka Broker.
- Each message is sent to a topic (e.g., `madhankafka`).
- The producer can send any number of messages, and multiple producers can send to the same topic.

# Topic Management (Producer Side):
- The producer must know the topic name to which it sends messages.
- Topics are usually created in Kafka before sending messages, but some Kafka setups allow auto-creation.
- The producer does not manage topic partitions or replication; it only sends messages to the topic.

---

# Key Components:

# Producer Side:
| Component | Purpose |
|-----------|---------|
| KafkaController | REST API endpoint to receive course data (POST /kafka/add-course) |
| KafkaService | Sends messages to Kafka topic using KafkaTemplate |
| Course Model | Data structure for course information |
| JsonSerializer | Serializes Course objects to JSON for transmission |
| application.yaml | Configuration for Kafka bootstrap servers & serialization |

---

# Note:
    This project implements a simple Kafka producer in a separate application
    that sends course information to a Kafka topic (madhankafka).

---