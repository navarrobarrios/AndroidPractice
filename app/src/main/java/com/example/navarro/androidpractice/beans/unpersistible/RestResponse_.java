package com.example.navarro.androidpractice.beans.unpersistible;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${Navarro} on 9/14/17.
 */
public class RestResponse_ {

    @SerializedName("messages")
    @Expose
    private List<String> messages = null;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public RestResponse_() {
    }

    /**
     *
     * @param result
     * @param messages
     */
    public RestResponse_(List<String> messages, List<Result> result) {
        super();
        this.messages = messages;
        this.result = result;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}