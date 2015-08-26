package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by akrmhrjn on 8/7/15.
 */

@Entity
public class Notification extends Model {

    @Id
    public String id;

    public String msg;


}
