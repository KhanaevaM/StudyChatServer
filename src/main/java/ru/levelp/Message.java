package ru.levelp;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
/**
 * Created by hanaevamaria on 30/11/16.
 */
public class Message {

    @Expose
    public String sender;
    @Expose
    public String receiver;
    @Expose
    public String body;
    @Expose
    public String timestamp;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
