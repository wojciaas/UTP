package zad1;

import java.util.List;

public class B extends Thread {
    private List<Towar> towary;

    public B(List<Towar> towary) {
        this.towary = towary;
    }

    @Override
    public void run() {
        int counter = 0;
        double totalWeight = 0;
        while(!Thread.currentThread().isInterrupted()) {
            synchronized (towary) {
                if (!towary.isEmpty()) {
                    Towar towar = towary.remove(0);
                    totalWeight += towar.getWeight();
                    counter++;
                    if (counter % 100 == 0) System.out.println("policzono wage " + counter + " towarów");
                }
            }
        }
        System.out.println(totalWeight);
    }
}
