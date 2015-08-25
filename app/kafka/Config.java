package kafka;

/**
 * Created by akrmhrjn on 8/19/15.
 */
public interface Config {

    String META_BROKER_LIST = "128.199.96.159:9092";
    String SERIALIZER_CLASS = "kafka.serializer.StringEncoder";
    String ZOOKEEPER_CONNECT = "128.199.96.159:2181";
    String GROUP_ID = "test-group";
    int NO_OF_MSG = 10;


}
