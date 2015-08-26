package kafka;

import kafka.producer.KeyedMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by akrmhrjn on 8/20/15.
 */
public class ProducerThread implements Runnable, Config {

    String topic;
    String msg;

    kafka.javaapi.producer.Producer<String, String> producer;
    ProducerThread(String topic,  kafka.javaapi.producer.Producer<String, String> producer, String message){
        this.topic = topic;
        this.producer = producer;
        this.msg = message;
    }
    public void run() {

        SimpleDateFormat sdf = new SimpleDateFormat();
        KeyedMessage<String, String> message = new KeyedMessage<String, String>(topic,msg);
        producer.send(message);

        System.out.println("Sending>>" + Thread.currentThread().getName() + " " + " " + sdf.format(new Date()) + ": " + msg);

        producer.close();

    }
}
