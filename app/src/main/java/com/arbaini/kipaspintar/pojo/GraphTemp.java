package com.arbaini.kipaspintar.pojo;

/**
 * Created by xplore on 11/22/17.
 */

public class GraphTemp {


        private String createdAt;
        private int entryId;
        private String field1;

    public GraphTemp(String createdAt, Integer entryId, String field1) {

        this.createdAt = createdAt;
        this.entryId = entryId;
        this.field1 = field1;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }
}
