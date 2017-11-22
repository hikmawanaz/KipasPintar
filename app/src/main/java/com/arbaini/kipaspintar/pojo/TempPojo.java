package com.arbaini.kipaspintar.pojo;

/**
 * Created by xplore on 11/22/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempPojo {



        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("entry_id")
        @Expose
        public Integer entryId;
        @SerializedName("field1")
        @Expose
        public String field1;


}
