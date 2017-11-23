package com.arbaini.kipaspintar;

/**
 * Created by xplore on 11/21/17.
 */
import com.arbaini.kipaspintar.pojo.StatusPower;
import com.arbaini.kipaspintar.pojo.TempPojo;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;



public interface APIInterfaces {

    @FormUrlEncoded
    @POST("/talkbacks/20406/commands.json")
    Call<StatusPower> setPower(@Field("api_key") String apikey,
                              @Field("command_string") String perintahState);

    @GET("/channels/{id}/field/1/last.json")
    Call<TempPojo> getTemp(@Path("id") int groupId);

    @GET("/channels/369167/feeds.json?results=5")
    Call<JSONResponse> getJSON();

}
