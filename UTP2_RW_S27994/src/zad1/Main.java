/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad1;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
                       .when( e -> e.startsWith("WAW")
                               /*<-- lambda wyrażenie
                                *  selekcja wylotów z Warszawy (zaczynających się od WAW)
                                */
                        )
                       .mapEvery( e -> {
                                    String[] tab = e.split(" ");
                                    return "to " + tab[1] + " - price in PLN:\t" + (int)(Integer.parseInt(tab[2]) * xrate);
                               }
                                  /*"to " + e.substring(4, 7)
                                  + " - price in PLN:\t"
                                  + (int)(Integer.parseInt(e.substring(8)) * xrate)*/
                                  /*<-- lambda wyrażenie
                                   *  wyliczenie ceny przelotu w PLN
                                   *  i stworzenie wynikowego napisu
                                   */
                        );
  }

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
