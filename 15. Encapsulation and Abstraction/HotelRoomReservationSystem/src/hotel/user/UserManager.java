package hotel.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager {
    public static final String USER_MODEL = "src/resources/users.csv";
    private final List<User> users = new ArrayList<>();

    public void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_MODEL))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    UUID userId = UUID.fromString(parts[0].trim());
                    String username = parts[1].trim();
                    String password = parts[2].trim();
                    boolean isAdmin = Boolean.parseBoolean(parts[3].trim());
                    users.add(new User(userId, username, password, isAdmin));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUsers() {
        try (FileWriter fw = new FileWriter(USER_MODEL)) {
            for (User user : users) {
                fw.write(user.getUserId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.isAdmin() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(String username, String password, boolean isAdmin) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        users.add(new User(username, password, isAdmin));
        saveUsers();
        return true;
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(User user) {
        users.removeIf(existingUser -> existingUser.getUsername().equals(user.getUsername()));
        users.add(user);
        saveUsers();
    }
}
