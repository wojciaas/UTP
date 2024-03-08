/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad1;


import java.util.List;

public interface Selector<T> { // Uwaga: interfejs musi być sparametrtyzowany
    public boolean select(T param);
}
