package zad3;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {

    private final Map<String, List<String>> LANGS;

    private final Map<String, List<String>> PROGRAMMERS;

    public ProgLang(String fname) throws IOException {
        PROGRAMMERS = new LinkedHashMap<>();
        this.LANGS = new LinkedHashMap<>();
        Scanner scanner = new Scanner(new File(fname));
        ArrayList<String> line;
        while (scanner.hasNext()) {
            line = new ArrayList<>(Arrays.asList(scanner.nextLine().split("\\t")));
            LANGS.put(line.get(0), new ArrayList<>(line.subList(1, line.size()).stream().distinct().collect(Collectors.toList())));
        }
        for (Map.Entry<String, List<String>> entryMap : LANGS.entrySet()) {
            for (String programmer : entryMap.getValue()) {
                if (!PROGRAMMERS.containsKey(programmer)) PROGRAMMERS.put(programmer, new ArrayList<>());
                PROGRAMMERS.get(programmer).add(entryMap.getKey());
            }
        }
    }

    Map<String, List<String>> getLangsMap() {
        return this.LANGS;
    }

    Map<String, List<String>> getProgsMap() {
        return PROGRAMMERS;
    }

    Map<String, List<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(LANGS, (l1, l2) -> l2.getValue().size() - l1.getValue().size());
    }

    Map<String, List<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(PROGRAMMERS, (p1, p2) -> {
                    if (!(p1.getValue().size() == p2.getValue().size())) return p2.getValue().size() - p1.getValue().size();
                    else return p1.getKey().compareTo(p2.getKey());
                });
    }

    Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int greaterThan) {
        return filtered(PROGRAMMERS, p -> p.getValue().size() > greaterThan);
    }

    private static <K, V> Map<K, List<V>> sorted(Map<K, List<V>> map, Comparator<Map.Entry<K, List<V>>> comparator) {
        return map.entrySet().stream().sorted(comparator).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue,
                LinkedHashMap::new
        ));
    }

    private static <K, V> Map<K, List<V>> filtered(Map<K, List<V>> map, Predicate<Map.Entry<K, List<V>>> predicate) {
        return map.entrySet().stream().filter(predicate).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue,
                LinkedHashMap::new
        ));
    }
}
