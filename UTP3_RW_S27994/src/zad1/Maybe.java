package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    private T value;

    Maybe(T value){
        this.value = value;
    }

    static <T>Maybe<T> of(T value){
        return new Maybe<>(value);
    }

    void ifPresent(Consumer<T> cons){
        if(isPresent()) cons.accept(value);
    }

    <R>Maybe<R> map(Function<T, R> func){
        return isPresent() ? new Maybe<>(func.apply(value)) : new Maybe<>(null);
    }

    T get(){
        if (!isPresent()) throw new NoSuchElementException(" maybe is empty");
        return value;
    }

    boolean isPresent(){
        return value != null;
    }

    T orElse(T defVal){
        return isPresent() ? value : defVal;
    }

    Maybe<T> filter(Predicate<T> pred){
        if (isPresent()) {
            return pred.test(value) ? this : new Maybe<>(null);
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        return isPresent() ? "Maybe has value " + value : "Maybe is empty";
    }
}
