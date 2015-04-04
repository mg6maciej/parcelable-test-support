package pl.mg6.testsupport;

import junit.framework.TestCase;

import pl.mg6.testsupport.data.Simple;
import pl.mg6.testsupport.factory.SimpleFactory;

public class FactoryReparcelerTestCase extends TestCase {

    private final FactoryReparceler factoryReparceler = new FactoryReparceler();

    public void testSimpleParcelableShouldBeEqual() {
        ReparcelingResultList<Simple> resultList = factoryReparceler.reparcel(SimpleFactory.class, Simple.class);
        for (ReparcelingResult<Simple> result : resultList) {
            assertNotNull(result.getOriginal());
            assertNotNull(result.getReparceled());
            assertNotSame(result.getOriginal(), result.getReparceled());
            assertTrue(result.areEqual());
            assertNull(result.getError());
        }
    }

    public void testFactoryReparcelerShouldFindAllMethods() {
        ReparcelingResultList<Simple> resultList = factoryReparceler.reparcel(SimpleFactory.class, Simple.class);
        assertEquals(3, resultList.size());
        assertEquals("withMaxValue", resultList.get(0).getMethodName());
        assertEquals("withPerfectNumber", resultList.get(1).getMethodName());
        assertEquals("withZero", resultList.get(2).getMethodName());
    }

    public void testFacotryReparcelerAllShouldBeEqual() {
        ReparcelingResultList<Simple> resultList = factoryReparceler.reparcel(SimpleFactory.class, Simple.class);
        assertTrue(resultList.areAllEqual());
    }
}
