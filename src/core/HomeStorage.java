package core;

import java.io.*;

public class HomeStorage {
    public static void save(SmartHome home, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(home);
            System.out.println("SmartHome saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SmartHome load(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            SmartHome home = (SmartHome) in.readObject();
            System.out.println("SmartHome loaded from file.");
            return home;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
