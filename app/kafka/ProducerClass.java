package kafka;


import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.util.Properties;

/**
 * Created by user on 8/4/14.
 */
public class ProducerClass implements Config {


    public ProducerClass(String topic, String message) {
        Properties properties = new Properties();
        properties.put("metadata.broker.list", META_BROKER_LIST);
        properties.put("serializer.class", SERIALIZER_CLASS);
        properties.put("request.required.acks", "1");
        ProducerConfig producerConfig = new ProducerConfig(properties);
        Producer<String, String> producer = new Producer<String, String>(producerConfig);
        Thread threadProducer = new Thread(new ProducerThread(topic, producer, message));
        threadProducer.start();
    }





    static {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.INFO);
        rootLogger.addAppender(new ConsoleAppender(
                new PatternLayout("%-6r [%p] %c - %m%n")));
    }



}


