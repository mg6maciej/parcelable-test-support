package pl.mg6.testsupport;

import junit.framework.TestCase;

import java.util.List;

import pl.mg6.testsupport.data.Simple;
import pl.mg6.testsupport.factory.SimpleFactory;

public class FactoryReparcelerTestCase extends TestCase {

    private final FactoryReparceler reparceler = new FactoryReparceler();

    public void testSimpleParcelableShouldBeEqual() {
        List<ReparcelingResult<Simple>> resultList = reparceler.reparcel(SimpleFactory.class, Simple.class);
        for (ReparcelingResult<Simple> result : resultList) {
            assertNotNull(result.getOriginal());
            assertNotNull(result.getReparceled());
            assertNotSame(result.getOriginal(), result.getReparceled());
            assertTrue(result.areEqual());
            assertNull(result.getError());
        }
    }

    public void testFactoryReparcelerShouldFindAllMethods() {
        List<ReparcelingResult<Simple>> resultList = reparceler.reparcel(SimpleFactory.class, Simple.class);
        assertEquals(3, resultList.size());
        assertEquals("withZero", resultList.get(2).getMethodName());
        assertEquals("withPerfectNumber", resultList.get(1).getMethodName());
        assertEquals("withMaxValue", resultList.get(0).getMethodName());
    }
}
