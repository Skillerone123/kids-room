package ua.softserveinc.tc.dto;

import ua.softserveinc.tc.constants.ModelConstants.DateConst;
import ua.softserveinc.tc.entity.Booking;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Петришак on 14.05.2016.
 */

public class BookingDTO  implements BaseDTO {



    private Long id;
    private String date;
    private String startTime;
    private String endTime;
    private String kidName;
    private String roomName;
    private Long duration;
    private Long sum;

    public BookingDTO() {
        this.id=id;
        this.startTime=startTime;

    }

    public BookingDTO(Booking booking){
        DateFormat df = new SimpleDateFormat(DateConst.DAY_MOUNTH_YEAR);
        this.date = df.format(booking.getBookingStartTime());

        df = new SimpleDateFormat(DateConst.TIME_FORMAT);
        this.startTime = df.format(booking.getBookingStartTime());
        this.endTime = df.format(booking.getBookingEndTime());

        this.kidName = booking.getIdChild().getFullName();
        this.roomName = booking.getIdRoom().getAddress();
        this.duration = booking.getDuration();
        this.sum = booking.getSum();
        this.id = booking.getIdBook();

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getKidName() {
        return kidName;
    }

    public void setKidName(String kidName) {
        this.kidName = kidName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
