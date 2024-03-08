package zad1;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class Letters{

    private List<Thread> threads = new CopyOnWriteArrayList<>();

    Letters(String letters) {
        Stream.of(letters.split("")).map(l -> new Thread(() -> {
           synchronized (this) {
               while (true) {
                   System.out.print(l);
                   try {
                       wait(1000);
                   } catch (InterruptedException e) {
                       return;
                   }
               }
           }
        }, "Thread " + l)).forEach(threads::add);
    }

    List<Thread> getThreads(){
        return threads;
    }
}
