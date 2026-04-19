## Setup Guide for Kafka Producer-Consumer Application

# Versions Used:
Spring Boot:   3.5.9
Java:          17
Kafka:         3.9.1
Spring Kafka:  3.9.1+ running on localhost:9092 from Spring Boot
Maven:         3.8.9+

---

# Local Setup Steps:
There are 2 paths you need to look at it and work on,
1. C:\Softwares\kafka\config - it has important configuration files
   server.properties - broker configuration (default is broker.id=0)
   - listeners listens to the port 9092
   - partition configuration (num.partitions=1) -> it's a log partitions per topic. More partitions allow greater parallelism for consumption.
   zookeeper.properties - port at which the clients will connect (clientPort=2181)

For easy run, copy these above 2 properties files from the config path and paste it in the bin/windows path but this is not a mandatory step to follow.

2. C:\Softwares\kafka\bin\windows - it has important batch files which needs to be run and execute with the help of their individual properties
   zookeeper-server-start.bat
   kafka-server-start.bat
   kafka-topics.bat

---

# Go to cmd and run as administrator with all 3 commands,
1. To start zookeeper server
   cd C:\Softwares\kafka\bin\windows>zookeeper-server-start.bat zookeeper.properties

2. To start kafka server
   cd C:\Softwares\kafka\bin\windows>kafka-server-start.bat server.properties
   while executing the kafka server bat, we may get an 'wmic' error.
   to resolve this error, go and open this file kafka-server-start.bat, and remove wmic like inside if condition, have only this line 'set KAFKA_HEAP_OPTS=-Xmx1G -Xms1G'
   now run the same command, it will work.

3. Create Kafka Topics
   Example 1: gayukafka topic with 3 partitions
   cd C:\Softwares\kafka\bin\windows
   kafka-topics.bat --create --topic gayukafka --bootstrap-server localhost:9092 --partitions 3

   Example 2: madhankafka topic with 2 partitions (Main Topic)
   cd C:\Softwares\kafka\bin\windows
   kafka-topics.bat --create --topic madhankafka --bootstrap-server localhost:9092 --partitions 2 --replication-factor 1

   Example 3: gayukafka topic with 1 partition and 2 replication factor
   cd C:\Softwares\kafka\bin\windows
   kafka-topics.bat --create --topic gayukafka --bootstrap-server localhost:9092 --partitions 1 --replication-factor 2

---