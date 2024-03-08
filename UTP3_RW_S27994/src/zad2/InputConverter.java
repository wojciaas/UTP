package zad2;

import java.util.function.Function;

public class InputConverter<T> {

    private T value;

    InputConverter(T value){
        this.value = value;
    }

    <R> R convertBy(Function... functions){
        Object result = value;
        for(Function function : functions){
            result = function.apply(result);
        }
        return (R) result;
    }
}
