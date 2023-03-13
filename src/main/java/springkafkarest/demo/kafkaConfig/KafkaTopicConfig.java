package springkafkarest.demo.kafkaConfig;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopicConfig {

    public NewTopic myTopic(){
        return TopicBuilder.name("add-employee").build();
    }
}
