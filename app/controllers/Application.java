package controllers;


import com.avaje.ebean.Model;
import com.google.gson.Gson;
import kafka.ConsumerDaemon;
import kafka.Message;
import kafka.ProducerClass;
import kafka.Utils;
import models.RegisterUrl;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

import static play.libs.Json.toJson;


public class Application extends Controller {

    public Result index() {
        return ok(index.render("Kafka Project."));
    }

    public Result registerUrl() {
        RegisterUrl registerURL = Form.form(RegisterUrl.class).bindFromRequest().get();
        registerURL.save();
        Message message = Utils.createMessage("web_crawl",System.currentTimeMillis(),"CrawlService",Form.form(RegisterUrl.class).bindFromRequest().get().url);
        String producedMessage = new Gson().toJson(message);
        new ProducerClass("Inserted",producedMessage);
        return redirect(routes.Application.getUrl());
    }

    public Result getUrl() {
        List<RegisterUrl> urls = new Model.Finder(String.class, RegisterUrl.class).all();
        return ok(toJson(urls));
    }

}
