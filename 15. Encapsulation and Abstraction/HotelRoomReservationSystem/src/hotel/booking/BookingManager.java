package hotel.booking;

import hotel.utils.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingManager {
    private final List<Booking> bookings;

    public BookingManager() {
        this.bookings = new ArrayList<>();
    }

    public List<Booking> getAllBookings() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.BOOKINGS_FILE_PATH))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                UUID userId = UUID.fromString(parts[0]);
                UUID bookingId = UUID.fromString(parts[1]);
                if (parts.length > 2) {
                    String status = parts[2].split(":")[0];
                    boolean isCancelled;
                    isCancelled = !status.equals("Booked");
                    String[] bookingDetails = parts[2].split(":")[1].split("\\|");
                    String hotelName = bookingDetails[0].trim();
                    int roomNumber = Integer.parseInt(bookingDetails[1].trim());
                    int nights = Integer.parseInt(bookingDetails[2].trim());
                    String checkInDate = bookingDetails[3].trim();
                    String checkOutDate = bookingDetails[4].trim();
                    double totalPrice = Double.parseDouble(bookingDetails[5].trim());
                    Booking booking = new Booking(userId,bookingId, hotelName, roomNumber, checkInDate, checkOutDate, nights, totalPrice, isCancelled);
                    bookings.add(booking);

                }
            }


        } catch (IOException e) {
            System.out.println("Error reading bookings file: " + e.getMessage());
        }

        return this.bookings;
    }

    public double[] calculateIncomeAndFees() {
        List<Booking> allBookings = this.getAllBookings();
        double[] calculations = new double[2];
        double totalIncome = 0;
        double totalFees = 0;
        for (Booking booking : allBookings) {
            totalIncome += booking.getTotalPrice();
            if (booking.isCancelled()) {
                totalFees += booking.getTotalPrice();
            }
        }
        calculations[0] = totalIncome;
        calculations[1] = totalFees;

        return calculations;
    }
}


