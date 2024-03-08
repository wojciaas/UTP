/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    List<Towar> towary = Collections.synchronizedList(new ArrayList<>());
    A A = new A(towary);
    B B = new B(towary);
    A.start();
    B.start();
    try {
      A.join();
      B.interrupt();
      B.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
