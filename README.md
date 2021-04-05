<h1 align="center">elasticsearch-spring-boot-samples</h1>

<p>This repository is the sample spring boot application which is connected to ElasticSearch.<p>

🎨 Setup Elasticsearch in Local
===============================

🎱 Download the Elasticsearch archive

```shell script
curl -L -O https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.12.0-darwin-x86_64.tar.gz
```

🎱 Extract the archive

```shell script
tar -xvf elasticsearch-7.12.0-darwin-x86_64.tar.gz
``` 

🎱 Start Elasticsearch from the bin directory:
```shell script
cd elasticsearch-7.12.0/bin
./elasticsearch
```

Once the elasticsearch is running, we can check the health of the cluster using the cat API
```shell script
curl -X GET "localhost:9200/_cat/health?v=true&pretty"
``` 
We get the below response. It shows yellow status as we are running only one instance. If you see yellow status, then your elasticsearch server is running.
```shell script
epoch      timestamp cluster       status node.total node.data shards pri relo init unassign pending_tasks max_task_wait_time active_shards_percent
1617634835 15:00:35  elasticsearch yellow          1         1      1   1    0    0        1             0                  -                 50.0%
```

🩸 Index Creation
==================
We have to run the below command to create the index called ***"employee"*** which is required for our application.

```shell script
curl -X PUT -u undefined:$ESPASS "localhost:9200/employee?pretty"
```
When we get the below response, it means the index is created.
```json
{
  "acknowledged" : true,
  "shards_acknowledged" : true,
  "index" : "employee"
}
```

🩸 Run the application
=======================
Now we are all set to run the application. Just run the below commands.

- ```
  mvn clean install
  ```
- ```
  java -jar target/elasticsearch-spring-boot-samples-0.0.1-SNAPSHOT.jar
  ```
  
**Note: Please check the associated blog post on the website** [spring-boot-with-elasticsearch](http://www.rahullokurte.com/spring-boot-with-elasticsearch/)









