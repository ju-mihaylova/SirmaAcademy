package hotel;

import hotel.booking.*;
import hotel.user.User;
import hotel.user.UserManager;
import hotel.utils.Constants;
import hotel.utils.InputReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class HotelReservationSystem {

    private static final UserManager userManager = new UserManager();
    private static User loggedInUser = null;
    private static final HotelManager hotelManager = new HotelManager();
    private static Hotel selectedHotel = null;

    public static void main(String[] args) {
        userManager.loadUsers();
        loadHotels();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (loggedInUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        registerUser();
                        break;
                    case 2:
                        loginUser();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                if (!loggedInUser.isAdmin()) {
                    menuPerUser(scanner);
                } else {
                    menuPerAdmin(scanner);
                }

            }

        }
    }

    private static void menuPerUser(Scanner scanner) {
        if (selectedHotel == null) {
            selectHotel();
        } else {
            System.out.println("1. View Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void menuPerAdmin(Scanner scanner) {
        System.out.println("1. View All Bookings");
        System.out.println("2. View Income And Fees");
        System.out.println("3. Add Room");
        System.out.println("4. Remove Room");
        System.out.println("5. Edit Room");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                viewAllBookings();
                break;
            case 2:
                viewIncomeAndFees();
                break;
            case 3: {
                String hotelName = InputReader.readString("Enter hotel name: ");
                int roomNumber = InputReader.readInteger("Enter room number: ");
                String roomType = InputReader.readString("Enter room type: ");
                int pricePerNight = InputReader.readInteger("Enter price per night: ");
                int cancellationFee = InputReader.readInteger("Enter cancellation fee: ");
                RoomManager.addRoom(hotelManager, hotelName, roomNumber, roomType, pricePerNight, cancellationFee);
                break;
            }
            case 4: {
                String hotelName = InputReader.readString("Enter hotel name: ");
                int roomNumber = InputReader.readInteger("Enter room number: ");
                RoomManager.removeRoom(hotelManager, hotelName, roomNumber);
                break;
            }
            case 5: {
                String hotelName = InputReader.readString("Enter hotel name: ");
                int roomNumber = InputReader.readInteger("Enter current room number: ");
                int newRoomNumber = InputReader.readInteger("Enter new room number: ");
                String newRoomType = InputReader.readString("Enter new room type: ");
                int newPricePerNight = InputReader.readInteger("Enter new price per night: ");
                int newCancellationFee = InputReader.readInteger("Enter new cancellation fee: ");
                RoomManager.editRoom(hotelManager, hotelName, roomNumber, newRoomNumber, newRoomType, newPricePerNight, newCancellationFee);
                break;
            }
            case 6:
                System.out.println("Exiting the program...");
                System.exit(0);

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void loadHotels() {
        Hotel rila = new Hotel("Rila");
        Hotel eurostars = new Hotel("Eurostars");
        Hotel millennium = new Hotel("Millennium");
        RoomManager roomManager1 = new RoomManager();
        RoomManager roomManager2 = new RoomManager();
        RoomManager roomManager3 = new RoomManager();
        roomManager1.loadRooms("Rila");
        roomManager2.loadRooms("Eurostars");
        roomManager3.loadRooms("Millennium");
        for (Room room : roomManager1.getRooms()) {
            rila.addRoom(room);
        }
        for (Room room : roomManager2.getRooms()) {
            eurostars.addRoom(room);
        }
        for (Room room : roomManager3.getRooms()) {
            millennium.addRoom(room);
        }
        hotelManager.addHotel(rila);
        hotelManager.addHotel(eurostars);
        hotelManager.addHotel(millennium);
    }

    private static void selectHotel() {
        System.out.println("Available Hotels:");
        for (Hotel hotel : hotelManager.getAllHotels()) {
            System.out.println(hotel.getName());
        }
        String hotelName = InputReader.readString("Enter hotel name: ");
        selectedHotel = hotelManager.getHotel(hotelName);
        if (selectedHotel == null) {
            System.out.println("Hotel not found. Please try again.");
        } else {
            System.out.println("Selected Hotel: " + selectedHotel.getName());
        }
    }

    private static void viewRooms() {
        List<Room> availableRooms = selectedHotel.getRooms();
        for (Room room : availableRooms) {
            System.out.println("Room Number: " + room.getRoomNumber());
            System.out.println("Type: " + room.getType());
            System.out.println("Price per Night: " + room.getPricePerNight());
            System.out.println("Cancellation Fee: " + room.getCancellationFee());
            System.out.println();
        }
    }

    private static void bookRoom() {
        String checkInDate = InputReader.readString("Enter check-in date (YYYY-MM-DD): ");
        String checkOutDate = InputReader.readString("Enter check-out date (YYYY-MM-DD): ");

        List<Room> availableRooms = getAvailableRooms(checkInDate, checkOutDate);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for the selected dates.");
            return;
        }

        System.out.println("Available Rooms:");
        for (Room room : availableRooms) {
            System.out.println("Room Number: " + room.getRoomNumber());
            System.out.println("Type: " + room.getType());
            System.out.println("Price per Night: " + room.getPricePerNight());
            System.out.println("Cancellation Fee: " + room.getCancellationFee());
            System.out.println();
        }

        int roomNumber = InputReader.readInteger("Enter room number to book: ");

        Room roomToBook = null;
        for (Room room : availableRooms) {
            if (room.getRoomNumber() == roomNumber) {
                roomToBook = room;
                break;
            }
        }

        if (roomToBook != null) {
            boolean booked = roomToBook.bookRoom(checkInDate, checkOutDate);
            if (booked) {
                String hotelName = selectedHotel.getName();
                int nights = calculateNights(checkInDate, checkOutDate);
                double totalPrice = nights * roomToBook.getPricePerNight();
                UUID bookingId = UUID.randomUUID();

                saveBooking(
                        loggedInUser.getUserId(),
                        bookingId,
                        hotelName,
                        roomNumber,
                        nights,
                        checkInDate,
                        checkOutDate,
                        totalPrice
                );

                System.out.println("Room booked successfully.");
            } else {
                System.out.println("Room is already booked for the selected dates.");
            }
        } else {
            System.out.println("Room number not found in the available rooms.");
        }
    }

    private static List<Room> getAvailableRooms(String checkInDate, String checkOutDate) {
        String hotelName = selectedHotel.getName();
        List<Room> availableRooms = new ArrayList<>();
        List<Integer> bookedRooms = new ArrayList<>();

        LocalDate checkIn = LocalDate.parse(checkInDate, DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));
        LocalDate checkOut = LocalDate.parse(checkOutDate, DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));

        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.BOOKINGS_FILE_PATH))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length > 2) {
                    String status = parts[2].split(":")[0];
                    String[] bookingDetails = parts[2].split(":")[1].split("\\|");

                    if (status.equals("Booked") && bookingDetails[0].equalsIgnoreCase(hotelName)) {
                        int roomNumber = Integer.parseInt(bookingDetails[1]);
                        LocalDate bookedCheckIn = LocalDate.parse(bookingDetails[3], DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));
                        LocalDate bookedCheckOut = LocalDate.parse(bookingDetails[4], DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));

                        if (!(checkOut.isBefore(bookedCheckIn) || checkIn.isAfter(bookedCheckOut))) {
                            bookedRooms.add(roomNumber);
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading reservations file: " + e.getMessage());
        }

        List<Room> allRooms = selectedHotel.getRooms();

        for (Room room : allRooms) {
            if (!bookedRooms.contains(room.getRoomNumber())) {
                availableRooms.add(room);
            }
        }

        return availableRooms;

    }


    private static void saveBooking(UUID userId,
                                    UUID bookingId,
                                    String hotelName,
                                    int roomNumber,
                                    int nights,
                                    String checkInDate,
                                    String checkOutDate,
                                    double totalPrice) {
        try (FileWriter writer = new FileWriter(Constants.BOOKINGS_FILE_PATH, true)) {
            writer.append("\n")
                    .append(userId.toString())
                    .append(",")
                    .append(bookingId.toString())
                    .append(",Booked:")
                    .append(hotelName).append("|")
                    .append(String.valueOf(roomNumber)).append("|")
                    .append(String.valueOf(nights)).append("|")
                    .append(checkInDate).append("|")
                    .append(checkOutDate).append("|")
                    .append(String.valueOf(totalPrice));
        } catch (IOException e) {
            System.out.println("Error saving booking: " + e.getMessage());
        }
    }

    private static int calculateNights(String checkInDate, String checkOutDate) {
        LocalDate checkIn = LocalDate.parse(checkInDate);
        LocalDate checkOut = LocalDate.parse(checkOutDate);
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    private static List<Booking> printUserBookings() {
        List<Booking> bookings = loggedInUser.getBookingHistory();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for the logged-in user.");
        } else {
            for (Booking booking : bookings) {
                    System.out.println("Reservation ID: " + booking.getBookingId());
                    System.out.println("Hotel Name: " + booking.getHotelName());
                    System.out.println("Room Number: " + booking.getRoomNumber());
                    System.out.println("Check-in Date: " + booking.getCheckInDate());
                    System.out.println("Check-out Date: " + booking.getCheckOutDate());
                    System.out.println("Nights: " + booking.getNights());
                    System.out.println();
            }
        }
        return bookings;
    }

    private static void cancelBooking() {
        System.out.println("Here are your current bookings:");
        List<Booking> activeBookings = printUserBookings();

        if (activeBookings.isEmpty()) {
            System.out.println("You have no active bookings to cancel.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter reservation ID to cancel booking: ");
        UUID bookingId = UUID.fromString(scanner.nextLine());

        Booking booking = loggedInUser.getBookingByBookingId(bookingId);
        if (booking != null) {
            booking.cancellation(loggedInUser.getUserId());
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Booking not found for this reservation ID.");
        }
    }
    private static void registerUser() {
        Scanner scanner = new Scanner(System.in);
        String username = InputReader.readString("Enter username: ");
        String password = InputReader.readString("Enter password: ");
        System.out.print("Do you have admin privileges: ");
        boolean isAdmin = (scanner.nextLine()).equalsIgnoreCase("yes");
        boolean success = userManager.registerUser(username, password, isAdmin);
        if (success) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    private static void loginUser() {
        String username = InputReader.readString("Enter username: ");
        String password = InputReader.readString("Enter password: ");
        User user = userManager.loginUser(username, password);
        if (user != null) {
            loggedInUser = user;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void viewAllBookings() {
        BookingManager bm = new BookingManager();
        List<Booking> allBookings = bm.getAllBookings();

        for(Booking booking : allBookings) {
            if (!booking.isCancelled()) {
                System.out.println("-----------------------------------");
                System.out.println("Hotel name: " + booking.getHotelName());
                System.out.println("Room number: " + booking.getRoomNumber());
                System.out.println("Check-in date: " + booking.getCheckInDate());
                System.out.println("Check-out date: " + booking.getCheckOutDate());
                System.out.println("Number of nights: " + booking.getNights());
                System.out.println("Total price: " + booking.getTotalPrice());
            }
        }
        System.out.println("-----------------------------------");
    }

    private static void viewIncomeAndFees() {
        BookingManager bm = new BookingManager();
        double[] calculatedIncomeAndFees = bm.calculateIncomeAndFees();
        double totalIncome = calculatedIncomeAndFees[0];
        double totalCancellationFees = calculatedIncomeAndFees[1];
        System.out.println("Total income: " + totalIncome);
        System.out.println("Total cancellation fees: " + totalCancellationFees);
    }
}




