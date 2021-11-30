package Functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public interface DataReader {

    static LinkedList<Long> readLongArray(String day) {
        LinkedList<Long> table = new LinkedList<>();
        String filePath = "data\\" + day + ".txt";
        File fileToRead = new File(filePath);
        try {
            Scanner fileScanner = new Scanner(fileToRead);
            fileScanner.useDelimiter("(\\s*,\\s*)|\\s+");
            while (fileScanner.hasNextLong()) {
                table.add(fileScanner.nextLong());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Plik nie istnieje");
            return new LinkedList<>();
        } catch (Exception e) {
            System.out.println("Coś innego nie pykło");
            return new LinkedList<>();
        }
        return table;
    }

    static LinkedList<String> readStringLine(String day) {
        LinkedList<String> list = new LinkedList<>();
        String filePath = "data\\" + day + ".txt";
        File fileToRead = new File(filePath);
        try {
            Scanner fileScanner = new Scanner(fileToRead);
            while (fileScanner.hasNextLine()) {
                String string = fileScanner.nextLine();
                list.addAll(List.of(string.split("\s*,\s*")));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Plik nie istnieje");
            return new LinkedList<>();
        } catch (Exception e) {
            System.out.println("Coś innego nie pykło");
            return new LinkedList<>();
        }
        return list;
    }
}
