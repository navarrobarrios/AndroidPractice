package com.example.navarro.androidpractice.beans.unpersistible;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${Navarro} on 9/14/17.
 */
public class RestUniqueResponse {

    @SerializedName("RestResponse")
    @Expose
    private RestUniqueResponse_ restResponse;

    /**
     * No args constructor for use in serialization
     *
     */
    public RestUniqueResponse() {
    }

    /**
     *
     * @param restResponse
     */
    public RestUniqueResponse(RestUniqueResponse_ restResponse) {
        super();
        this.restResponse = restResponse;
    }

    public RestUniqueResponse_ getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestUniqueResponse_ restResponse) {
        this.restResponse = restResponse;
    }

}