package springkafkarest.demo.kafkaConfig;


import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.internals.Topic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopicConfig {

    public NewTopic myTopic(){

        TopicBuilder builder = TopicBuilder.name("employee-events");
        builder.partitions(3);
        builder.replicas(2);
        return builder.build();
    }
}
