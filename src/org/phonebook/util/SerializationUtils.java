package org.phonebook.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class SerializationUtils {

    public static void serialize(Object object, String fileName) throws IOException {
        Path path = Path.of(fileName).toAbsolutePath();
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(path, StandardOpenOption.WRITE));
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        oos.close();
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        Path path = Path.of(fileName).toAbsolutePath();
        if (!Files.exists(path)) {
            Files.createFile(path);
            return new ArrayList<>();
        }
        BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(path, StandardOpenOption.READ));
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object object = ois.readObject();
        ois.close();
        return object;
    }
}
