## Configuration (application.yaml)

server:
port: 8082                           # REST API port

spring:
kafka:
bootstrap-servers: localhost:9092  # Kafka broker address
consumer:
group-id: consumer-group         # Consumer group for offset management
auto-offset-reset: earliest       # Start from beginning if no offset exists
key-deserializer: StringDeserializer      # Keys are strings
value-deserializer: JsonDeserializer      # Values are JSON format

properties:
spring:
    json:
        use:
            type:
                headers: false      # Don't use type info from message headers
        value:
            default:
                type: com.event.consumer.model.Course  # Always deserialize to this class

---

# Problem Encountered & Solution

# The Problem
java.lang.ClassNotFoundException: com.event.producer.model.Course

# Why: Producer and consumer have different Course classes in different packages. When Kafka tried to deserialize using type headers, it couldn't find producer's class in consumer's classpath.

# The Solution
use:
    type:
        headers: false
value:
    default:
        type: com.event.consumer.model.Course

---

# How it works:
- Ignore type headers in the message
- Always use consumer's Course class for deserialization
- Jackson automatically maps JSON fields to Course object
- Both classes must have same field names and types

---

# Result
- Consumer can now deserialize messages from producer without having producer's application.

---