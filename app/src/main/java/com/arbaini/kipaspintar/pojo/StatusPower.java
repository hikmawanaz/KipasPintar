package com.arbaini.kipaspintar.pojo;

/**
 * Created by xplore on 11/21/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusPower {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("command_string")
    @Expose
    public String commandString;
    @SerializedName("position")
    @Expose
    public Integer position;
    @SerializedName("executed_at")
    @Expose
    public Object executedAt;
    @SerializedName("created_at")
    @Expose
    public String createdAt;

}