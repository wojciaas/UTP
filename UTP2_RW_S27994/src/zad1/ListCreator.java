package zad1;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListCreator<E> {

    private List<E> list;

    ListCreator (List<E> list){
        this.list = list;
    }

    static <E> ListCreator<E> collectFrom(List<E> list) {
        return new ListCreator<>(list);
    }

    ListCreator<E> when(Predicate<E> selector){
        list = list.stream().filter(selector).collect(Collectors.toList());
        return this;
    }

    List<E> mapEvery(Function<E, E> mapper){
        return list.stream().map(mapper).collect(Collectors.toList());
    }
}
