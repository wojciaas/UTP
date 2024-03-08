/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad1;


public interface Mapper<E, T> { // Uwaga: interfejs musi być sparametrtyzowany
    public T map(E param);
}
