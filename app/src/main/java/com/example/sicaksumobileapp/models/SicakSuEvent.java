package com.example.sicaksumobileapp.models;

import java.util.Date;
import java.util.List;

public class SicakSuEvent {
    String id;
    String content;
    String headline;
    int limit;
    int joinCount;
    List<SicakSuProfile> joinedPeople;

    public SicakSuEvent() {
        this.id = "None";
        this.content = "None";
        this.headline = "None";
        this.limit = 0;
        this.joinCount = 0;
        this.joinedPeople = null;
    }

    public SicakSuEvent(String id, String content, String headline, int limit, int joinCount, List<SicakSuProfile> joinedPeople) {
        this.id = id;
        this.content = content;
        this.headline = headline;
        this.limit = limit;
        this.joinCount = joinCount;
        this.joinedPeople = joinedPeople;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(int joinCount) {
        this.joinCount = joinCount;
    }

    public List<SicakSuProfile> getJoinedPeople() {
        return joinedPeople;
    }

    public void setJoinedPeople(List<SicakSuProfile> joinedPeople) {
        this.joinedPeople = joinedPeople;
    }

    @Override
    public String toString() {
        return "SicakSuEvent{" +
                "content='" + content + '\'' +
                ", headline='" + headline + '\'' +
                ", limit=" + limit +
                ", joinCount=" + joinCount +
                '}';
    }

}
