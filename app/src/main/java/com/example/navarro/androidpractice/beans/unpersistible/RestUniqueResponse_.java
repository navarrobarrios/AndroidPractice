package com.example.navarro.androidpractice.beans.unpersistible;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ${Navarro} on 9/14/17.
 */
public class RestUniqueResponse_ {

    @SerializedName("messages")
    @Expose
    private List<String> messages = null;
    @SerializedName("result")
    @Expose
    private Result result = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public RestUniqueResponse_() {
    }

    /**
     *
     * @param result
     * @param messages
     */
    public RestUniqueResponse_(List<String> messages, Result result) {
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}