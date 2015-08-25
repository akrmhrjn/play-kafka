package kafka;

import java.io.Serializable;
import java.util.List;

/**
 * Created by akrmhrjn on 8/24/15.
 */
public class Message implements Serializable {

    String app_source_id;
    long created_at;
    String type;
    Payload payload;

}
