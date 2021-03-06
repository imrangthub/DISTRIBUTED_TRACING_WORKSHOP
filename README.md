
Zipkin with MySQL Database
==========================================================================================

        ALTER USER 'root'@'localhost'   IDENTIFIED WITH mysql_native_password   BY 'root';

#Run Jar with Env

        java -jar zipkin-server-2.12.9-exec.jar --zipkin.storage.type=mysql --zipkin.storage.mysql.host=localhost --zipkin.storage.mysql.port=3306 --   zipkin.storage.mysql.username=root --zipkin.storage.mysql.password=root --zipkin.storage.mysql.db=zipkin_service_mysql
        
        java -jar zipkin-server-2.12.9-exec.jar --server.port=9410 --zipkin.storage.type=cassandra3 --zipkin.storage.cassandra3.ensure-schema=true --zipkin.storage.cassandra3.contact-points=127.0.0.1:9042 --zipkin.storage.cassandra3.username=imran --zipkin.storage.cassandra3.password=123456 --zipkin.storage.cassandra3.keyspace=dev_kespc


#Zipkin Required Table SQL Script:

        CREATE TABLE IF NOT EXISTS zipkin_spans (
          `trace_id_high` BIGINT NOT NULL DEFAULT 0 COMMENT 'If non zero, this
        means the trace uses 128 bit traceIds instead of 64 bit',
          `trace_id` BIGINT NOT NULL,
          `id` BIGINT NOT NULL,
          `name` VARCHAR(255) NOT NULL,
          `remote_service_name` VARCHAR(255),
          `parent_id` BIGINT,
          `debug` BIT(1),
          `start_ts` BIGINT COMMENT 'Span.timestamp(): epoch micros used for
        endTs query and to implement TTL',
          `duration` BIGINT COMMENT 'Span.duration(): micros used for
        minDuration and maxDuration query',
          PRIMARY KEY (`trace_id_high`, `trace_id`, `id`)
        ) ENGINE=InnoDB ROW_FORMAT=COMPRESSED CHARACTER SET=utf8 COLLATE
        utf8_general_ci;
        ALTER TABLE zipkin_spans ADD INDEX(`trace_id_high`, `trace_id`)
        COMMENT 'for getTracesByIds';
        ALTER TABLE zipkin_spans ADD INDEX(`name`) COMMENT 'for getTraces and
        getSpanNames';
        ALTER TABLE zipkin_spans ADD INDEX(`remote_service_name`) COMMENT 'for
        getTraces and getRemoteServiceNames';
        ALTER TABLE zipkin_spans ADD INDEX(`start_ts`) COMMENT 'for getTraces
        ordering and range';
        
        
        CREATE TABLE IF NOT EXISTS zipkin_annotations (
          `trace_id_high` BIGINT NOT NULL DEFAULT 0 COMMENT 'If non zero, this
        means the trace uses 128 bit traceIds instead of 64 bit',
          `trace_id` BIGINT NOT NULL COMMENT 'coincides with
        zipkin_spans.trace_id',
          `span_id` BIGINT NOT NULL COMMENT 'coincides with zipkin_spans.id',
          `a_key` VARCHAR(255) NOT NULL COMMENT 'BinaryAnnotation.key or
        Annotation.value if type == -1',
          `a_value` BLOB COMMENT 'BinaryAnnotation.value(), which must be
        smaller than 64KB',
          `a_type` INT NOT NULL COMMENT 'BinaryAnnotation.type() or -1 if
        Annotation',
          `a_timestamp` BIGINT COMMENT 'Used to implement TTL;
        Annotation.timestamp or zipkin_spans.timestamp',
          `endpoint_ipv4` INT COMMENT 'Null when Binary/Annotation.endpoint is
        null',
          `endpoint_ipv6` BINARY(16) COMMENT 'Null when
        Binary/Annotation.endpoint is null, or no IPv6 address',
          `endpoint_port` SMALLINT COMMENT 'Null when
        Binary/Annotation.endpoint is null',
          `endpoint_service_name` VARCHAR(255) COMMENT 'Null when
        Binary/Annotation.endpoint is null'
        ) ENGINE=InnoDB ROW_FORMAT=COMPRESSED CHARACTER SET=utf8 COLLATE
        utf8_general_ci;
        ALTER TABLE zipkin_annotations ADD UNIQUE KEY(`trace_id_high`,
        `trace_id`, `span_id`, `a_key`, `a_timestamp`) COMMENT 'Ignore insert
        on duplicate';
        ALTER TABLE zipkin_annotations ADD INDEX(`trace_id_high`, `trace_id`,
        `span_id`) COMMENT 'for joining with zipkin_spans';
        ALTER TABLE zipkin_annotations ADD INDEX(`trace_id_high`, `trace_id`)
        COMMENT 'for getTraces/ByIds';
        ALTER TABLE zipkin_annotations ADD INDEX(`endpoint_service_name`)
        COMMENT 'for getTraces and getServiceNames';
        ALTER TABLE zipkin_annotations ADD INDEX(`a_type`) COMMENT 'for
        getTraces and autocomplete values';
        ALTER TABLE zipkin_annotations ADD INDEX(`a_key`) COMMENT 'for
        getTraces and autocomplete values';
        ALTER TABLE zipkin_annotations ADD INDEX(`trace_id`, `span_id`,
        `a_key`) COMMENT 'for dependencies job';
        
        
      CREATE TABLE IF NOT EXISTS zipkin_dependencies (
        `day` DATE NOT NULL,
        `parent` VARCHAR(255) NOT NULL,
        `child` VARCHAR(255) NOT NULL,
        `call_count` BIGINT,
        `error_count` BIGINT,
        PRIMARY KEY (`day`, `parent`, `child`)
      ) ENGINE=InnoDB ROW_FORMAT=COMPRESSED CHARACTER SET=utf8 COLLATE
      utf8_general_ci;


#application.yml/application.properties

Cassandra:

        server:
          port: 9410
        spring:
          application:
            name: zipkin-server
          datasource:
            schema: classpath:/cassandra3-schema.cql
            url: jdbc:cassandra://127.0.0.1:9042
            initialize: true
            continue-on-error: true
          cloud:
            config:
              uri: http://localhost:7085
        management:
          endpoints:
            web:
              exposure:
                include: "*"
        zipkin:
          storage:
            type: cassandra3
            cassandra3:
              ensure-schema: true
              contact-points: localhost
              keyspace: zipkin_server_kespc
              username: cassandra
              password: cassandra


                      
MySQL:

        server:
          port: 9410

        spring:
          application:
            name: app-zipkin-service
          datasource:
            schema: classpath:/mysql.sql
            url: jdbc:mysql://localhost:3306/zipkin_service_mysql?autoReconnect=true&useSSL=false
            username: root
            password: root
            driver-class-name: com.mysql.jdbc.Driver

        zipkin:
          storage:
            type: mysql
            mysql:
              host: localhost
              port: 3306
              username: root
              password: root
              db: zipkin_service_mysql

      
Zipkin with CassandraStorage
==========================================================================================
       java -jar zipkin-server-2.12.9-exec.jar --zipkin.storage.type=cassandra3
       
       
        =>cqlsh
        =>cqlsh localhost -u cassandra -p cassandra
        =>describe keyspaces;
        =>use zipkin2;
        =>describe tables;

Table List:

         dependency  
         trace_by_service_span  
         span  
         autocomplete_tags  
         span_by_service
         
         =>select * from trace_by_service_span;
         =>select * from span;
         =>select * from autocomplete_tags;
         =>select * from span_by_service;
 





Zipkin URL
============


http://localhost:9410/zipkin/

http://localhost:9410/health

http://localhost:9410/actuator/info

http://localhost:9410/metrics

http://localhost:9410/actuator/prometheus


Service config:
-------------------------

        spring:
          application:
            name: 'active-listener'
          profiles: 'dev'
          sleuth:
            async:
              enabled: false
            annotation:
              enabled: true
          enabled: true
          sampler:
            probability: 1.0
          zipkin:
            baseUrl: http://localhost:9411
            enabled: true
            sender:
              type: web*
              
              
			
			
			 
Kafka		
===================================================

                => cd C:\Z_MY_COMPUTER\Software\kafka


                Change dir for log
                
                C:\Z_MY_COMPUTER\Software\kafka\data\zookeeper



                =>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
                
                =>.\bin\windows\kafka-server-start.bat .\config\server.properties



                Create a topic with name imranmadbar-topic, that has only one partition & one replica.
                
                =>.\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:2181 --replication-factor 1 --partitions 1 --topic imranmadbartopic



			   
			   

