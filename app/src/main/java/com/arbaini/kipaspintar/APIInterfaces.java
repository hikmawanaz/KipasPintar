package com.arbaini.kipaspintar;

/**
 * Created by xplore on 11/21/17.
 */
import com.arbaini.kipaspintar.pojo.StatusPower;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface APIInterfaces {

    @FormUrlEncoded
    @PUT("/talkbacks/20406/commands/9643913.json")
    Call<StatusPower> setPower(@Field("api_key") String apikey,
                              @Field("command_string") String perintahState);

}
