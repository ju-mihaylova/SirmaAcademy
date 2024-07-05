package hotel.booking;

import hotel.Hotel;
import hotel.HotelManager;
import hotel.utils.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    private List<Room> rooms = new ArrayList<>();

    public List<Room> getRooms() {
        return rooms;
    }

    public void loadRooms(String hotelName) {
        rooms = getAllRooms(hotelName);
    }


    public static List<Room> getAllRooms(String hotelName) {
        String hotelFileName = hotelName.toLowerCase().replace(" ", "_") + "_rooms.csv";
        String hotelFilePath = Constants.ROOMS_FILE_PATH_PREFIX + hotelFileName;

        List<Room> allRooms = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(hotelFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                int roomNumber = Integer.parseInt(parts[0]);
                String roomType = parts[1];
                double pricePerNight = Double.parseDouble(parts[2]);
                double cancellationFee = Double.parseDouble(parts[3]);
                Room room = new Room(roomNumber, RoomType.valueOf(roomType), pricePerNight, cancellationFee);
                allRooms.add(room);
            }
        } catch (IOException e) {
            System.out.println("Error reading room file: " + e.getMessage());
        }

        return allRooms;
    }



    public static void addRoom(
            HotelManager hotelManager,
            String hotelName,
            int roomNumber,
            String roomType,
            int pricePerNight,
            int cancellationFee
    ) {
        String hotelFileName = hotelName.toLowerCase().replace(" ", "_") + "_rooms.csv";
        String hotelFilePath = Constants.ROOMS_FILE_PATH_PREFIX + hotelFileName;

        List<Room> allRooms = getAllRooms(hotelName);
        for (Room room : allRooms) {
            if (room.getRoomNumber() == roomNumber) {
                System.out.println("Room " + roomNumber + " already exists in " + hotelName);
                return;
            }
        }

        Hotel selectedHotel = hotelManager.getHotel(hotelName);
        Room newRoom = new Room(roomNumber, RoomType.valueOf(roomType), pricePerNight, cancellationFee);
        selectedHotel.addRoom(newRoom);

        try (FileWriter writer = new FileWriter(hotelFilePath, true)) {
            writer.append(String.valueOf(roomNumber))
                    .append(",")
                    .append(roomType)
                    .append(",")
                    .append(String.valueOf(pricePerNight))
                    .append(",")
                    .append(String.valueOf(cancellationFee))
                    .append("\n");
            System.out.println("Room added successfully to " + hotelFileName);
        } catch (IOException e) {
            System.out.println("Error adding room to file: " + e.getMessage());
        }

    }

    private static void writeRoomsToFile(String hotelFilePath, List<Room> rooms) {
        try (FileWriter writer = new FileWriter(hotelFilePath)) {
            writer.write("roomNumber,type,pricePerNight,cancellationFee\n");
            for (Room room : rooms) {
                writer.write(room.getRoomNumber() + "," +
                        room.getType() + "," +
                        room.getPricePerNight() + "," +
                        room.getCancellationFee() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to room file: " + e.getMessage());
        }
    }

    public static void removeRoom(HotelManager hotelManager, String hotelName, int roomNumber) {
        String hotelFileName = hotelName.toLowerCase().replace(" ", "_") + "_rooms.csv";
        String hotelFilePath = Constants.ROOMS_FILE_PATH_PREFIX + hotelFileName;

        List<Room> allRooms = getAllRooms(hotelName);
        boolean roomFound = false;

        for (Room room : allRooms) {
            if (room.getRoomNumber() == roomNumber) {
                allRooms.remove(room);
                Hotel selectedHotel = hotelManager.getHotel(hotelName);
                selectedHotel.removeRoom(roomNumber);
                roomFound = true;
                break;
            }
        }

        if (!roomFound) {
            System.out.println("Room " + roomNumber + " not found in " + hotelFileName);
            return;
        }

        writeRoomsToFile(hotelFilePath, allRooms);
        System.out.println("Room " + roomNumber + " removed successfully from " + hotelFileName);
    }

    public static void editRoom(HotelManager hotelManager, String hotelName, int roomNumber, int newRoomNumber, String newRoomType, int newPricePerNight, int newCancellationFee) {
        String hotelFileName = hotelName.toLowerCase().replace(" ", "_") + "_rooms.csv";
        String hotelFilePath = Constants.ROOMS_FILE_PATH_PREFIX + hotelFileName;

        List<Room> allRooms = getAllRooms(hotelName);
        boolean roomFound = false;

        for (int i = 0; i < allRooms.size(); i++) {
            Room room = allRooms.get(i);
            if (room.getRoomNumber() == roomNumber) {
                room.setRoomNumber(newRoomNumber);
                room.setType(RoomType.valueOf(newRoomType));
                room.setPricePerNight(newPricePerNight);
                room.setCancellationFee(newCancellationFee);
                allRooms.set(i, room);

                Hotel selectedHotel = hotelManager.getHotel(hotelName);
                Room selectedRoom = selectedHotel.getRoom(roomNumber);
                selectedRoom.setRoomNumber(newRoomNumber);
                selectedRoom.setType(RoomType.valueOf(newRoomType));
                selectedRoom.setPricePerNight(newPricePerNight);
                selectedRoom.setCancellationFee(newCancellationFee);
                roomFound = true;
                break;
            }
        }

        if (!roomFound) {
            System.out.println("Room " + roomNumber + " not found in " + hotelFileName);
            return;
        }

        writeRoomsToFile(hotelFilePath, allRooms);
        System.out.println("Room " + roomNumber + " edited successfully in " + hotelFileName);
    }
}
