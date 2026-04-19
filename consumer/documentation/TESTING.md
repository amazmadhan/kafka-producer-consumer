# Testing Guide for Kafka Consumer Application

# KAFKA CONSUMER CODE to write:
    Get the message JSON (course object) from the kafka topic
    Deserialize to the course object
    Return and display the same course object in the JSON response
    
    API - GET method

---

## Testing:
1. Build Project
mvn clean install

2. Run Application
mvn spring-boot:run

Console:
Started ConsumerApplication in X.XXX seconds
Listening to topic madhankafka
Consumer group: consumer-group

3. Hit REST API endpoint in Postman
- Retrieves the latest course message consumed from Kafka
- Method: GET (no authentication needed)
- Endpoint: http://localhost:8082/kafka/get-course

Console Output:
    Course {courseId='Cid-0001', title='Sample Course Title', trainer='John Doe', price=99.99} Got the data from kafka

---

## Monitor Consumer Group:

# Check group status
kafka-consumer-groups --bootstrap-server localhost:9092 --group consumer-group --describe

# Reset offset to beginning
kafka-consumer-groups --bootstrap-server localhost:9092 --group consumer-group --reset-offsets --to-earliest --topic madhankafka --execute

---

## Default Ports & Configs:

| Component | Port | URL |
|-----------|------|-----|
| Consumer App | 8082 | http://localhost:8082 |
| Kafka Broker | 9092 | localhost:9092 |
| Topic | - | madhankafka |
| Consumer Group | - | consumer-group |

---

## Testing Checklist :
- Kafka broker running (localhost:9092)
- Topic `madhankafka` created
- Application starts without errors
- Consumer group `consumer-group` created
- API returns 200 OK
- Messages properly deserialized
- No errors in logs

---

## Response Codes:

| Code | Meaning |
|------|---------|
| 200 | Success - Message retrieved |
| 500 | Server error |

---

# Note:
The consumer don't want to know about the producer details, we just want to consume the message from the topic and display it in the API response.
(our producer application running on the port 8081, just saying for clarity)
The consumer must know about the topic name (madhankafka) and the consumer group (consumer-group) to consume messages from the Kafka broker. That's it.
The consumer receives messages from the topic `madhankafka` using the GET API at `http://localhost:8081/kafka/get-course`.
This confirms that the course data has been received from the Kafka topic by the consumer.

---