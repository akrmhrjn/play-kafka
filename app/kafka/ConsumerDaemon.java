package kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by akrmhrjn on 8/24/15.
 */
public class ConsumerDaemon implements Daemon, Config{

    ConsumerConnector consumerConnector;
    String TOPIC_INSERT = "Inserted";
    private ExecutorService executorService = Executors.newSingleThreadExecutor();


    @Override
    public void init(DaemonContext daemonContext) throws DaemonInitException, Exception {
        System.out.println("initializing ...");
        Properties properties = new Properties();
        properties.put("zookeeper.connect",ZOOKEEPER_CONNECT);
        properties.put("group.id",GROUP_ID);
        ConsumerConfig consumerConfig = new ConsumerConfig(properties);
        consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
    }

    @Override
    public void start() throws Exception {
        System.out.println("starting ...");
        this.executorService.execute(new Runnable(){

            public void run() {
                Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
                topicCountMap.put(TOPIC_INSERT, new Integer(1));
                Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
                KafkaStream<byte[], byte[]> stream = consumerMap.get(TOPIC_INSERT).get(0);

                ConsumerIterator<byte[], byte[]> it = stream.iterator();
                while (it.hasNext())
                {
                    System.out.println("Receiving>>" + new String(it.next().message()));
                }
            }
        });
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stopping ...");
        this.executorService.shutdown();
    }

    @Override
    public void destroy() {
        System.out.println("done.");
    }
}
