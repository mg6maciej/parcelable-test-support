package pl.mg6.testsupport;

import android.os.Parcelable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Modifier.isStatic;

public class FactoryReparceler {

    public <T extends Parcelable> List<ReparcelResult<T>> reparcel(Class<?> factoryClass, Class<T> parcelableClass) {
        List<ReparcelResult<T>> resultList = new ArrayList<>();
        Reparceler reparceler = new Reparceler();
        for (Method m : factoryClass.getMethods()) {
            if (isStatic(m.getModifiers()) && m.getParameterTypes().length == 0 && m.getReturnType() == parcelableClass) {
                ReparcelResult<T> result;
                String methodName = m.getName();
                try {
                    T original = (T) m.invoke(null);
                    result = reparceler.reparcel(original, methodName);
                } catch (Throwable error) {
                    result = new ReparcelResult<>(null, null, false, methodName, error);
                }
                resultList.add(result);
            }
        }
        return resultList;
    }
}
