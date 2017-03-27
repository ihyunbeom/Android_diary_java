package com.devStereo.owls.diary;

/**
 * Created by Cloud on 2017-03-12.
 */

public class Item {
    private  int id;
    private String topic;
    private  String contents;

    public Item(int id, String topic, String contents) {
        this.id = id;
        this.topic = topic;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
