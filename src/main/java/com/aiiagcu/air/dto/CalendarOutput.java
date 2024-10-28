package com.aiiagcu.air.dto;

import com.aiiagcu.air.util.GoogleCalendarUtil;
import com.google.api.services.calendar.model.Event;
import lombok.Data;


@Data
public class CalendarOutput {
    private String id;
    private String start;
    private String end;
    private String title;
    private String location;
    
    public CalendarOutput(Event event) {
        this.id = event.getId();
        this.start = GoogleCalendarUtil.convertEventDateTimeToLocalDateTime(event.getStart()).toString().replace('T', ' ');
        this.end = GoogleCalendarUtil.convertEventDateTimeToLocalDateTime(event.getEnd()).toString().replace('T', ' ');
        this.title = event.getSummary();
        this.location = event.getLocation();
    }
}
