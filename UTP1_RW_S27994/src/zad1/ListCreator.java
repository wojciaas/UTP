/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana

    private List<T> list;

    public ListCreator(List<T> src) {
        this.list = src;
    }

    public static <T> ListCreator<T> collectFrom(List<T> src){
        return new ListCreator<>(new ArrayList<>(src));
    }

    public ListCreator<T> when(Selector<T> selector) {
        list = list.stream().filter(selector::select).collect(Collectors.toList());
        return this;
    }

    public List<Integer> mapEvery(Mapper<T, Integer> mapper) {
        return list.stream().map(mapper::map).collect(Collectors.toList());
    }
}  
