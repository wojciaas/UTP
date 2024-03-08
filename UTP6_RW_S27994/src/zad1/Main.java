/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad1;


public class Main {

  public static void main(String[] args) throws InterruptedException {
    Letters letters = new Letters("ABCD");
    for (Thread t : letters.getThreads()) System.out.println(t.getName());
    for (Thread thread : letters.getThreads()) thread.start();
    /*<- tu uruchomić
         wszystkie kody w wątkach 
     */

    Thread.sleep(5000);

    for (Thread thread : letters.getThreads()) thread.interrupt();
    /*<- tu trzeba zapisać
       fragment, który kończy działanie kodów, wypisujących litery 
    */
    System.out.println("\nProgram skończył działanie");
  }

}
