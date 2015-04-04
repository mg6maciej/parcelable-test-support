package pl.mg6.testsupport;

import android.os.Parcelable;

import java.util.Iterator;
import java.util.List;

public class ReparcelingResultList<T extends Parcelable> implements Iterable<ReparcelingResult<T>> {

    private final List<ReparcelingResult<T>> results;

    public ReparcelingResultList(List<ReparcelingResult<T>> results) {
        this.results = results;
    }

    public ReparcelingResult<T> get(int location) {
        return results.get(location);
    }

    @Override
    public Iterator<ReparcelingResult<T>> iterator() {
        return results.iterator();
    }

    public int size() {
        return results.size();
    }
}
