package com.zoro.studdis.data;

public class EventsModel extends Model {

    private String title, text, date, time, loc;

    public EventsModel(int id, String title, String text, String date, String time, String loc) {
        super(id);
        this.title = title;
        this.text = text;
        this.date = date;
        this.time = time;
        this.loc = loc;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLoc() {
        return loc;
    }
}
