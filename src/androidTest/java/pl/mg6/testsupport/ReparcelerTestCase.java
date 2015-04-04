package pl.mg6.testsupport;

import android.os.Parcelable;

import junit.framework.TestCase;

import org.parceler.Parcels;

import java.util.Arrays;
import java.util.List;

import pl.mg6.testsupport.data.Complex;
import pl.mg6.testsupport.data.Hrisey;
import pl.mg6.testsupport.data.Parceler;
import pl.mg6.testsupport.data.Simple;

public class ReparcelerTestCase extends TestCase {

    private final Reparceler reparceler = new Reparceler();

    public void testSimpleParcelableShouldBeEqual() {
        Simple simple = new Simple(6);
        ReparcelingResult<Simple> result = reparceler.reparcel(simple);
        assertSame(simple, result.getOriginal());
        assertNotNull(result.getReparceled());
        assertNotSame(simple, result.getReparceled());
        assertTrue(result.areEqual());
        assertNull(result.getError());
    }

    public void testHriseyParcelableShouldBeEqual() {
        Hrisey hrisey = new Hrisey(42, "Answer");
        ReparcelingResult<Hrisey> result = reparceler.reparcel(hrisey);
        assertSame(hrisey, result.getOriginal());
        assertNotNull(result.getReparceled());
        assertNotSame(hrisey, result.getReparceled());
        assertTrue(result.areEqual());
        assertNull(result.getError());
    }

    public void testParcelerParcelableShouldBeEqual() {
        Parceler parceler = new Parceler(256, "MAX");
        Parcelable parcelable = Parcels.wrap(parceler);
        ReparcelingResult<Parcelable> result = reparceler.reparcel(parcelable);
        assertSame(parcelable, result.getOriginal());
        assertNotNull(result.getReparceled());
        assertNotSame(parcelable, result.getReparceled());
        assertTrue(result.areEqual());
        assertNull(result.getError());
    }

    public void testComplexParcelableShouldBeEqual() {
        Simple simple = new Simple(6);
        Hrisey hrisey = new Hrisey(42, "Answer");
        List<String> list = Arrays.asList("One", "Two", "Three");
        Complex complex = new Complex(simple, hrisey, list);
        ReparcelingResult<Complex> result = reparceler.reparcel(complex);
        assertSame(complex, result.getOriginal());
        assertNotNull(result.getReparceled());
        assertNotSame(complex, result.getReparceled());
        assertTrue(result.areEqual());
        assertNull(result.getError());
    }
}
