package controllers;


import com.avaje.ebean.Model;
import com.google.gson.Gson;
import kafka.*;
import models.Notification;
import models.RegisterUrl;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.notification;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static play.libs.Json.toJson;


public class Application extends Controller {

    public Result index() {
        return ok(index.render("Kafka Project."));
    }

    public Result registerUrl() {
        RegisterUrl registerURL = Form.form(RegisterUrl.class).bindFromRequest().get();
        registerURL.save();
        Utils.sendToKafka("Inserted", "crawler", Form.form(RegisterUrl.class).bindFromRequest().get().url);
        return redirect(routes.Application.getUrl());
    }

    public Result getUrl() {
        List<RegisterUrl> urls = new Model.Finder(String.class, RegisterUrl.class).all();
        return ok(toJson(urls));
    }

    public Result notification() {
        return ok(notification.render("Kafka Project."));
    }

    public Result notificationMsg() {
        Notification notification = Form.form(Notification.class).bindFromRequest().get();
        notification.save();
        Utils.sendToKafka("degendra", "notification", Form.form(Notification.class).bindFromRequest().get().msg);
        return redirect(routes.Application.getNotification());
    }

    public Result getNotification() {
        List<Notification> messages = new Model.Finder(String.class, Notification.class).all();
        return ok(toJson(messages));
    }


}
