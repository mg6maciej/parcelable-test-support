package pl.mg6.testsupport;

import junit.framework.TestCase;

import pl.mg6.testsupport.data.WithNonStaticCreator;
import pl.mg6.testsupport.data.WithProtectedCreator;

public class ReparcelerErrorTestCase extends TestCase {

    private final Reparceler reparceler = new Reparceler();

    public void testWithProtectedCreatorShouldFail() {
        WithProtectedCreator original = new WithProtectedCreator();
        ReparcelingResult<WithProtectedCreator> result = reparceler.reparcel(original);
        assertSame(original, result.getOriginal());
        assertNull(result.getReparceled());
        assertNotNull(result.getError());
        assertEquals("Missing public static CREATOR field on class WithProtectedCreator.", result.getError().getMessage());
    }

    public void testWithoutCreatorShouldFail() {
        WithNonStaticCreator original = new WithNonStaticCreator();
        ReparcelingResult<WithNonStaticCreator> result = reparceler.reparcel(original);
        assertSame(original, result.getOriginal());
        assertNull(result.getReparceled());
        assertNotNull(result.getError());
        assertEquals("Missing public static CREATOR field on class WithNonStaticCreator.", result.getError().getMessage());
    }
}
