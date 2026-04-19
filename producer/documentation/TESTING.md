## Testing Guide for Kafka Producer Application

# KAFKA PRODUCER CODE to write:
    Create the message JSON (course object)
    Serialize the course object
    Send the same JSON to the kafka topic
    
    API - POST method

---

# Testing:
1. Build Project
   mvn clean install

2. Run Application
   mvn spring-boot:run

Console:
Started ConsumerApplication in X.XXX seconds

3. Hit REST API endpoint in Postman
- Sends the latest course message publishing to Kafka
- Method: POST (no authentication needed)
- Endpoint: http://localhost:8081/kafka/add-course
- Request Body:
    {
     "courseId": "Cid-0001",
     "title": "Sample Course Title",
     "trainer": "John Doe",
     "price": 99.99
    }

Console Output:
    Course message sent to kafka server

---

# Default Ports & Configs:

| Component      | Port | URL/Name                  |
|---------------|------|---------------------------|
| Producer App  | 8081 | http://localhost:8081     |
| Kafka Broker  | 9092 | localhost:9092            |
| Topic         |  -   | madhankafka               |

---

# Testing Checklist :
- Kafka broker running (localhost:9092)
- Topic `madhankafka` created
- Application starts without errors
- API returns 200 OK
- Messages properly serialized and sent
- No errors in logs

---

# Response Codes:

| Code | Meaning                        |
|------|--------------------------------|
| 200  | Success - Message sent         |
| 500  | Server error                   |

---

# Note:
The producer sends messages to the topic `madhankafka` using the POST API at `http://localhost:8081/kafka/add-course`.
This confirms that the course data has been sent to the Kafka topic by the producer 
which can then be consumed by the consumer application.
(our consumer application running on the port 8082)

---