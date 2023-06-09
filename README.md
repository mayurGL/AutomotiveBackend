<h1 align="center" id="title">Automotive Backend Service</h1>

<p align="center"><img src="https://socialify.git.ci/mayurGL/AutomotiveBackend/image?font=Inter&amp;forks=1&amp;issues=1&amp;language=1&amp;name=1&amp;pattern=Floating%20Cogs&amp;pulls=1&amp;stargazers=1&amp;theme=Dark" alt="project-image"></p>

<p id="description">The Automotive Backend Service is a robust solution designed to manage a fleet of vehicles and handle the continuous stream of data generated by them.</p>

<p id="description">The system utilizes Apache Kafka and Apache Cassandra to ensure the persistence of real-time and historical data. By seamlessly integrating with IoT Hubs and employing a RESTful API, the service enables efficient data transmission and storage. Fleet managers can leverage the stored data for further processing, visualization and optimization of fleet operations.</p>

<h2>🧐 Features</h2>

Here're some of the project's best features:

*   Real -Time data handling
*   Fast Data writes and access
*   Optimal Security
*   Reliable data using current and historical tables
*   RESTful APIs for client side interactions


<h2>🚀 Demo</h2>

[For consumer application] http://localhost:8085
[For Swagger API docs] http://localhost:8085/swagger-ui/index.html
[For producer application] http://localhost:8080

<h2>💻 Built with</h2>

Technologies used in the project:

*   Spring Boot ( using Java )
*    Java
*    Python
*   Apache Kafka
*   Apache Cassandra
*   Postman

<h2>🛠️ Installation Steps:</h2>

<p>1. Download Apache Kafka tgz file</p>

```
https://kafka.apache.org/downloads   
```

<p>2. Extract the file and paste it into C: drive</p>

<p>3. Download Apache Cassandra version 3.11.X</p>

```
https://www.apache.org/dyn/closer.lua/cassandra/3.11.15/apache-cassandra-3.11.15-bin.tar.gz
```

<p>4. Download Java 8 (preferably Oracle) according to your OS</p>

```
https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html
```

<p>5. Download Python 2.7 according to your OS</p>

```
https://www.python.org/downloads/release/python-2718/
```

<p>6. Download any IDE like intelliJ etc. to run the code</p>

```
https://www.jetbrains.com/idea/download/#section=windows
```

<h2>⚙ Execution Steps:</h2>

<p>1. First Start the Zookeeper | Open Kafka installation directory | Open CMD/terminal</p>

```
if you are on windows navigate to bin/windows and write the following command

zookeeper-server-start.bat C:\YOUR_KAFKA_FOLDER_NAME\zookeeper.properties
```
<p>2. After that start Kafka Server | Open a new CMD/terminal in same path</p>

```
kafka-server-start.bat C:\YOUR_KAFKA_FOLDER_NAME\server.properties
```
<p>3. Start Cassandra Server | Open Cassandra installation directory | navigate to bin folder | Open CMD/terminal</p>

```
cassandra.bat -f
```
<p>4. Start the Producer Application</p>

```
Producer application source code: https://github.com/mayurGL/AutomotiveProducer
```
<p>5. Start the consumer Application</p>

```
Consumer application source code:  https://github.com/mayurGL/AutomotiveBackend
```


