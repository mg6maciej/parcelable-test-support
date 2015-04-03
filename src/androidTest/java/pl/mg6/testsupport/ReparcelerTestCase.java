package pl.mg6.testsupport;

import junit.framework.TestCase;

import pl.mg6.testsupport.data.Simple;

public class ReparcelerTestCase extends TestCase {

    private final Reparceler reparceler = new Reparceler();

    public void testSimpleParcelableShouldBeEqual() {
        Simple simple = new Simple(6);
        ReparcelResult<Simple> result = reparceler.reparcel(simple);
        assertSame(simple, result.getOriginal());
        assertNotSame(simple, result.getReparceled());
        assertTrue(result.areEqual());
        assertNull(result.getError());
    }
}
