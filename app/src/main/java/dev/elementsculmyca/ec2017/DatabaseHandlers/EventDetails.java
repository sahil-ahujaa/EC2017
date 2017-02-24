package dev.elementsculmyca.ec2017.DatabaseHandlers;

/**
 * Created by hemba on 2/4/2017.
 */

public class EventDetails {

    private String eventName,club,category,description,rules,venue,fee,
            startTime,endTime,eventId;

    public EventDetails(String eventId,String eventName, String club, String category
            , String description, String rules, String venue, String fee
            , String startTime, String endTime) {
        this.eventId=eventId;
        this.eventName = eventName;
        this.club = club;
        this.category = category;
        this.description = description;
        this.rules = rules;
        this.venue = venue;
        this.fee = fee;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getClub() {
        return club;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getRules() {
        return rules;
    }

    public String getVenue() {
        return venue;
    }

    public String getFee() {
        return fee;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

}
