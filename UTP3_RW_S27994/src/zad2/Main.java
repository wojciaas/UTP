/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad2;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {

    Function<String, List<String>> flines = fname -> {
      try {
        BufferedReader breader = new BufferedReader(new FileReader(fname));
        return breader.lines().collect(Collectors.toList());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    };
    Function<List<String>, String> join = list -> String.join("", list);
    Function<String, List<Integer>> collectInts = text -> Stream.of(text.split("[^0-9]+"))
            .filter(value -> value.matches("\\d+"))
            .map(Integer::valueOf)
            .collect(Collectors.toList());
    Function<List<Integer>, Integer> sum = integers -> integers.stream().reduce(0, Integer::sum);
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
