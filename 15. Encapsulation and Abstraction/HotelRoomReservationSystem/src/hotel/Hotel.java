package hotel;

import hotel.booking.Room;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private final String name;
    private final List<Room> rooms;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(int roomNumber) {
        for(Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                rooms.remove(room);
                break;
            }
        }
    }

    public Room getRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}

