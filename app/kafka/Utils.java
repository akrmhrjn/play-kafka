package kafka;

import com.google.gson.Gson;
/**
 * Created by akrmhrjn on 8/24/15.
 */
public class Utils implements Config{

    public static Message createMessage(String app_source_id, long created_at, String type, String url, String name, String email, String msg, String msgType){
        Message message = new Message();
        message.app_source_id = app_source_id;
        message.created_at = created_at;
        message.type = type;
        Payload payload = new Payload();
        if(url!=null)
            payload.URL = url;
        if(name!=null)
            payload.name = name;
        if(email!=null)
            payload.email = email;
        if(msg!=null)
            payload.msg = msg;
        if(msgType!=null)
            payload.msg_type = msgType;
        message.payload = payload;
        return message;
    }

    public static void sendToKafka(String topic, String from, String messageFromController){
        Message message = new Message();
        switch (from) {
            case "crawler":
                message = Utils.createMessage(from, System.currentTimeMillis(), "CrawlerService", messageFromController, null, null, null, null);
                break;
            case "notification":
                message = Utils.createMessage(from, System.currentTimeMillis(), "NotificationService", null, "Degendra", "degendra@bajratechnologies.com",messageFromController, "error");
                break;
            default:
        }
        String producedMessage = new Gson().toJson(message);
        new ProducerClass(topic, producedMessage);
    }



}
