package hotel.user;

import hotel.booking.Booking;
import hotel.booking.BookingManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private final UUID userId;
    private final String username;
    private final String password;
    private boolean isAdmin;

    private List<Booking> bookingHistory;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.userId = UUID.randomUUID();
        this.bookingHistory = new ArrayList<>();
    }

    public User(UUID userId, String username, String password, boolean isAdmin) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.bookingHistory = new ArrayList<>();
    }

    public UUID getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    
    public List<Booking> getBookingHistory() {
        BookingManager bm = new BookingManager();
        List<Booking> allBookings = bm.getAllBookings();
        List<Booking> activeBookings = new ArrayList<>();
        UUID userId = getUserId();

        for (Booking booking : allBookings) {
            if (booking.getUserId().equals(userId) && !booking.isCancelled()) {
                activeBookings.add(booking);
            }
        }

        this.bookingHistory = activeBookings;
        return activeBookings;
    }

    public Booking getBookingByBookingId(UUID bookingId) {
        for (Booking booking : bookingHistory) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        return null;
    }

}

