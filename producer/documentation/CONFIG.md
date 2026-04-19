## Configuration (application.yaml)

This file configures the Spring Boot Kafka producer application.

# Server Settings
- `server.port`: The port number where the application runs (default: 8081).

# Spring Application
- `spring.application.name`: The name of the application (set to `producer`).

# Kafka Configuration
- `spring.kafka.bootstrap-servers`: The Kafka server address (default: `localhost:9092`).
- `spring.kafka.producer.key-serializer`: Class used to serialize the key of the message (StringSerializer).
- `spring.kafka.producer.value-serializer`: Class used to serialize the value of the message (JsonSerializer).

---

# Note:
- Make sure the Kafka server is running at the specified address.
- These settings ensure that the producer can send JSON messages to Kafka topics.

---