package dev.elementsculmyca.ec2017.Utility;

/**
 * Created by hemba on 2/16/2017.
 */

public class TicketDetails {
    String qrCode,eventName;

    public TicketDetails(String qrCode, String eventName) {
        this.qrCode = qrCode;
        this.eventName = eventName;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getEventName() {
        return eventName;
    }
}
