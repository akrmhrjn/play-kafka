package kafka;


import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;


/**
 * Created by user on 8/4/14.
 */
public class ConsumerClass implements Runnable, Config {

    ConsumerConnector consumerConnector;
    String TOPIC_INSERT = "Inserted";

    public ConsumerClass(){
        Properties properties = new Properties();
        properties.put("zookeeper.connect",ZOOKEEPER_CONNECT);
        properties.put("group.id",GROUP_ID);
        ConsumerConfig consumerConfig = new ConsumerConfig(properties);
        consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
    }


    public static void main(String[] argv) throws UnsupportedEncodingException {
        Thread consumer = new Thread(new ConsumerClass());
        consumer.start();
    }


    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(TOPIC_INSERT, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream =  consumerMap.get(TOPIC_INSERT).get(0);

        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while(it.hasNext()) {
            System.out.println("Receiving>>" + new String(it.next().message()));
        }
    }

    static
    {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.INFO);
        rootLogger.addAppender(new ConsoleAppender(
                new PatternLayout("%-6r [%p] %c - %m%n")));
    }
}
