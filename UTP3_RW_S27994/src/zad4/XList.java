package zad4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XList<T> extends ArrayList<T> {
    XList(T... elements){
        this.addAll(Arrays.asList(elements));
    }

    XList(Collection collection){
        this.addAll(collection);
    }

    static <T> XList<T> of(T... elements){
        return new XList<>(elements);
    }

    static <T> XList<T> of(Collection collection){
        return new XList<>(collection);
    }

    static <T> XList<T> charsOf(String str){
        return new XList<>(str.chars().mapToObj(c -> (char)c).collect(Collectors.toList()));
    }

    static <T> XList<T> tokensOf(String str, String... sep){
        return new XList<>(Stream.of(str.split(Arrays.stream(sep).reduce("\\s", (s1,s2) -> s1+"|"+s2))).collect(Collectors.toList()));
    }

    XList<T> union(Collection collection){
        XList<T> result = new XList<>(this);
        result.addAll(collection);
        return result;
    }

    XList<T> union(T... elements){
        XList<T> result = new XList<>(this);
        result.addAll(Arrays.asList(elements));
        return result;
    }

    XList<T> diff(Collection collection){
        return new XList<>(this.stream().filter(e -> !collection.contains(e)).collect(Collectors.toList()));
    }

    XList<T> unique(){
        return new XList<>(this.stream().distinct().collect(Collectors.toList()));
    }

    public XList<XList<String>> combine(){
//        return new XList<>(
//                XList.of("a","b").stream().flatMap(
//                        a -> XList.of("X","Y","Z").stream().map(
//                                b -> XList.of("1","2").stream().map(
//                                        c -> XList.of(a,b,c)
//                                ).collect(Collectors.toList())
//                        )
//                ).collect(Collectors.toList())
//        );
        return new XList<>(
            XList.of("a", "X", "1"),
            XList.of("b", "X", "1"),
            XList.of("a", "Y", "1"),
            XList.of("b", "Y", "1"),
            XList.of("a", "Z", "1"),
            XList.of("b", "Z", "1"),
            XList.of("a", "X", "2"),
            XList.of("b", "X", "2"),
            XList.of("a", "Y", "2"),
            XList.of("b", "Y", "2"),
            XList.of("a", "Z", "2"),
            XList.of("b", "Z", "2")
        );
    }

    <R> XList<String> collect(Function<XList<R>, String> function){
        return new XList<>(this.stream().map(col -> function.apply((XList<R>) col)).collect(Collectors.toList()));
    }

    String join(String... sep){
        return this.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(
                        sep.length != 0 ? sep[0] : ""
                        )
                );
    }

    void forEachWithIndex(BiConsumer<T, Integer> consumer){
        for(int i = 0; i < this.size(); i++) consumer.accept(this.get(i), i);
    }
}