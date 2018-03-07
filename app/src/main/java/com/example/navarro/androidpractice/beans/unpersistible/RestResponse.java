package com.example.navarro.androidpractice.beans.unpersistible;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${Navarro} on 9/14/17.
 */
public class RestResponse {

    @SerializedName("RestResponse")
    @Expose
    private RestResponse_ restResponse;

    /**
     * No args constructor for use in serialization
     *
     */
    public RestResponse() {
    }

    /**
     *
     * @param restResponse
     */
    public RestResponse(RestResponse_ restResponse) {
        super();
        this.restResponse = restResponse;
    }

    public RestResponse_ getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestResponse_ restResponse) {
        this.restResponse = restResponse;
    }

}