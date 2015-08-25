package kafka;

/**
 * Created by akrmhrjn on 8/24/15.
 */
public class Utils {

    public static Message createMessage(String app_source_id, long created_at, String type, String url){
        Message message = new Message();
        message.app_source_id = app_source_id;
        message.created_at = created_at;
        message.type = type;
        Payload payload = new Payload();
        payload.url = url;
        message.payload = payload;
        return message;
    }
}
