[![Codacy Badge](https://api.codacy.com/project/badge/Grade/bab2210534304c81b1f7e11b213b052e)](https://www.codacy.com/app/philipgold/kafka-spark-twitter-stream-demo?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=philipgold/kafka-spark-twitter-stream-demo&amp;utm_campaign=Badge_Grade)


# Kafka Spark Twitter Stream Demo
A demo project using Spark Streaming to analyze popular hashtags from the twitter data streams. The data comes from the Twitter Streaming API source and is fed to Kafka. The consumer service receives data from Kafka and then processes it in a stream using Spark Streaming.
![Sequence Diagram](https://github.com/philipgold/kafka-spark-twitter-stream-demo/raw/master/img/mircroservice-kafka-data-streams-with-spark.png) 



## Requirements
* Apache Maven 3.x
* JVM 8
* Docker machine
* Registered an Twitter Application. The following guides may also be helpful: [How to create a Twitter application.](http://docs.inboundnow.com/guide/create-twitter-application/)

## Quickstart guide
1. Change Twitter configuration in `\twitter-producer\src\main\resources\application.properties`: 
![twitter-producter-app-properies](https://github.com/philipgold/kafka-spark-twitter-stream-demo/raw/master/img/twitter-producter-app-properies.png) 

2. Run docker-compose with following command: `docker-compose up -d`
3. Check if ZooKeeper and Kafka is running (from command prompt)
4. Launch twitter-producer app:
```
$ cd twitter-producer
$ mvn spring-boot:run
```
5. Launch spark-consumer app: 
```
$ cd twitter-producer
$ mvn spring-boot:run
```
You'll see results:
![spark-consumer-console-results](https://raw.githubusercontent.com/philipgold/kafka-spark-twitter-stream-demo/master/img/spark-consumer-console-results.png) 

## References
* [Spring for Apache Kafka](https://projects.spring.io/spring-kafka/)
* [Spring Social Twitter](http://projects.spring.io/spring-social-twitter/)
* [Spark Overview](http://spark.apache.org/docs/latest/)
* [Apache Kafka Documentation](http://kafka.apache.org/documentation.html)
* [Big Data Processing with Apache Spark - Part 3: Spark Streaming](https://www.infoq.com/articles/apache-spark-streaming)
* [Spring Kafka - Embedded Unit Test Example](https://www.codenotfound.com/spring-kafka-embedded-unit-test-example.html)