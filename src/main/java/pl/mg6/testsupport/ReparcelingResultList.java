package pl.mg6.testsupport;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReparcelingResultList<T extends Parcelable> implements Iterable<ReparcelingResult<T>> {

    private final List<ReparcelingResult<T>> results;

    ReparcelingResultList(List<ReparcelingResult<T>> results) {
        this.results = results;
    }

    public boolean areAllEqual() {
        return getIssues().size() == 0;
    }

    public ReparcelingResult<T> get(int location) {
        return results.get(location);
    }

    public ReparcelingResultList<T> getIssues() {
        List<ReparcelingResult<T>> issues = new ArrayList<>();
        for (ReparcelingResult<T> result : results) {
            if (!result.areEqual()) {
                issues.add(result);
            }
        }
        return new ReparcelingResultList<>(issues);
    }

    @Override
    public Iterator<ReparcelingResult<T>> iterator() {
        return results.iterator();
    }

    public int size() {
        return results.size();
    }
}
