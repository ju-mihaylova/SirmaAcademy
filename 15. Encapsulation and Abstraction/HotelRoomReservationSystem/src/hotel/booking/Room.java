package hotel.booking;

import hotel.utils.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomNumber;
    private RoomType type;
    private double pricePerNight;
    private double cancellationFee;
    private List<String> bookingDates;

    public Room(int roomNumber, RoomType type, double pricePerNight, double cancellationFee) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.cancellationFee = cancellationFee;
        this.bookingDates = new ArrayList<>();

    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public List<String> getBookingDates() {
        return bookingDates;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public void setBookingDates(List<String> bookingDates) {
        this.bookingDates = bookingDates;
    }

    public boolean bookRoom(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        List<String> range = DateUtils.getDatesBetween(start, end, formatter);

        for(String date : range) {
            if (bookingDates.contains(date)) {
                return false;
            }
        }

        bookingDates.addAll(range);
        return true;
    }
}
