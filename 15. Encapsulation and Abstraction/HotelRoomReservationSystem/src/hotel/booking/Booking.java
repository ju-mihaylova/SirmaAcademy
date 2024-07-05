package hotel.booking;

import hotel.audit.Audit;
import hotel.utils.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booking {
    private final UUID userId;
    private final UUID bookingId;
    private final String hotelName;
    private final int roomNumber;
    private final String checkInDate;
    private final String checkOutDate;
    private final int nights;
    private final double totalPrice;
    private boolean isCancelled;

    public Booking(
            UUID userId,
            UUID bookingId,
            String hotelName,
            int roomNumber,
            String checkInDate,
            String checkOutDate,
            int nights,
            double totalPrice,
            boolean isCancelled
    ) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.hotelName = hotelName;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.nights = nights;
        this.totalPrice = totalPrice;
        this.isCancelled = isCancelled;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public int getNights() {
        return nights;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        isCancelled = true;
    }



    public void cancellation(UUID userId) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.BOOKINGS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 2 && parts[0].equals(userId.toString()) && parts[1].equals(getBookingId().toString())) {
                    String bookingDetails = parts[2];
                    String[] bookingParts = bookingDetails.split("\\|");

                    if (bookingParts[0].startsWith("Booked:")) {
                        String hotelName = bookingParts[0].substring(7);
                        int roomNumber = Integer.parseInt(bookingParts[1]);
                        double cancellationFee = getCancellationFee(hotelName, roomNumber);

                        String newBookingDetails = "Cancelled:" + hotelName + "|" + roomNumber + "|" + bookingParts[2] + "|" + bookingParts[3] + "|" + bookingParts[4] + "|" + cancellationFee;
                        lines.add(parts[0] + "," + parts[1] + "," + newBookingDetails);
                    } else {
                        lines.add(line);
                    }
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }


        try (FileWriter writer = new FileWriter(Constants.BOOKINGS_FILE_PATH)) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }


        Audit.logInfo("Reservation with ID " + bookingId + " for user " + userId + " has been cancelled");
    }

    private static double getCancellationFee(String hotelName, int roomNumber) {
        String hotelFileName = hotelName.toLowerCase().replace(" ", "_") + "_rooms.csv";
        String hotelFilePath = Constants.ROOMS_FILE_PATH_PREFIX + hotelFileName;

        try (BufferedReader reader = new BufferedReader(new FileReader(hotelFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                int currentRoomNumber = Integer.parseInt(parts[0]);
                if (currentRoomNumber == roomNumber) {
                    return Double.parseDouble(parts[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading room file: " + e.getMessage());
        }
        return 0.0;
    }
}