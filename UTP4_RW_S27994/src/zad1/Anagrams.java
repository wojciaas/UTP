/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagrams {

    private HashMap<String, List<String>> anagrams;

    public Anagrams(String path) throws FileNotFoundException {
        HashMap<String, List<String>> anagrams = new HashMap<>();
        for (String word : new BufferedReader(new FileReader(path)).lines().flatMap(
                                                line -> Stream.of(line.split("\\s"))
                                                ).collect(Collectors.toList()))
        {
            String tmpKey = sortCh(word);
            if (!anagrams.containsKey(tmpKey)) anagrams.put(tmpKey, new ArrayList<>());
            anagrams.get(tmpKey).add(word);
        }
        this.anagrams = anagrams;
    }

    private static String sortCh(String word) {
        char[] wordCharArray = word.toCharArray();
        Arrays.sort(wordCharArray);
        return String.valueOf(wordCharArray);
    }

    Iterable<List<String>> getSortedByAnQty() {
        return anagrams.values().stream().sorted(
                (l1, l2) -> l1.size() == l2.size() ? l2.get(0).compareTo(l1.get(0)) : l2.size() - l1.size()
        ).collect(Collectors.toList());
    }

    String getAnagramsFor(String word){
        return word + ": " + anagrams.get(sortCh(word)).stream().filter(e -> !e.equals(word)).collect(Collectors.toList());
    }
}
