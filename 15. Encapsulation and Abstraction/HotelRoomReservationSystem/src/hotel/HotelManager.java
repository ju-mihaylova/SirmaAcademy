package hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    private final List<Hotel> hotels;

    public HotelManager() {
        this.hotels = new ArrayList<>();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public Hotel getHotel(String name) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(name)) {
                return hotel;
            }
        }
        return null;
    }


    public List<Hotel> getAllHotels() {
        return hotels;
    }
}
