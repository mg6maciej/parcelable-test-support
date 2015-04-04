package pl.mg6.testsupport;

import android.os.Parcelable;

import junit.framework.TestCase;

import org.parceler.Parcels;

import pl.mg6.testsupport.data.Hrisey;
import pl.mg6.testsupport.data.Parceler;
import pl.mg6.testsupport.data.Simple;
import pl.mg6.testsupport.data.WithNonStaticCreator;
import pl.mg6.testsupport.data.WithProtectedCreator;

public class ReparcelerTestCase extends TestCase {

    private final Reparceler reparceler = new Reparceler();

    public void testSimpleParcelableShouldBeEqual() {
        Simple simple = new Simple(6);
        ReparcelingResult<Simple> result = reparceler.reparcel(simple);
        assertSame(simple, result.getOriginal());
        assertNotSame(simple, result.getReparceled());
        assertTrue(result.areEqual());
        assertNull(result.getError());
    }

    public void testHriseyParcelableShouldBeEqual() {
        Hrisey hrisey = new Hrisey(42, "Answer");
        ReparcelingResult<Hrisey> result = reparceler.reparcel(hrisey);
        assertSame(hrisey, result.getOriginal());
        assertNotSame(hrisey, result.getReparceled());
        assertTrue(result.areEqual());
        assertNull(result.getError());
    }

    public void testParcelerParcelableShouldBeEqual() {
        Parceler parceler = new Parceler(256, "MAX");
        Parcelable parcelable = Parcels.wrap(parceler);
        ReparcelingResult<Parcelable> result = reparceler.reparcel(parcelable);
        assertSame(parcelable, result.getOriginal());
        assertNotSame(parcelable, result.getReparceled());
        assertTrue(result.areEqual());
        assertNull(result.getError());
    }

    public void testWithProtectedCreatorShouldFail() {
        WithProtectedCreator original = new WithProtectedCreator();
        ReparcelingResult<WithProtectedCreator> result = reparceler.reparcel(original);
        assertSame(original, result.getOriginal());
        assertNull(result.getReparceled());
        assertTrue(result.getError() instanceof ReparcelingError);
        assertEquals("Missing public static CREATOR field on class WithProtectedCreator.", result.getError().getMessage());
    }

    public void testWithoutCreatorShouldFail() {
        WithNonStaticCreator original = new WithNonStaticCreator();
        ReparcelingResult<WithNonStaticCreator> result = reparceler.reparcel(original);
        assertSame(original, result.getOriginal());
        assertNull(result.getReparceled());
        assertTrue(result.getError() instanceof ReparcelingError);
        assertEquals("Missing public static CREATOR field on class WithNonStaticCreator.", result.getError().getMessage());
    }
}
