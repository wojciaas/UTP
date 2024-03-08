package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class A extends Thread {
    private final List<Towar> towary;

    public A(List<Towar> towary) {
        this.towary = towary;
    }

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new FileReader("../Towary.txt"))) {
            int counter = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(" ");
                int id = Integer.parseInt(info[0]);
                double weight = Double.parseDouble(info[1]);
                synchronized (towary) {
                    towary.add(new Towar(id, weight));
                    counter++;
                    if (counter % 200 == 0) System.out.println("utworzono " + counter + " obiektów");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
