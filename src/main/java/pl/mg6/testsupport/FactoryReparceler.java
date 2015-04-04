package pl.mg6.testsupport;

import android.os.Parcelable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Modifier.isStatic;

public class FactoryReparceler {

    public <T extends Parcelable> List<ReparcelingResult<T>> reparcel(Class<?> factoryClass, Class<T> parcelableClass) {
        List<ReparcelingResult<T>> resultList = new ArrayList<>();
        for (Method method : factoryClass.getMethods()) {
            if (staticNoParamsReturningParcelable(method, parcelableClass)) {
                ReparcelingResult<T> result = reparcel(method);
                resultList.add(result);
            }
        }
        return resultList;
    }

    private static boolean staticNoParamsReturningParcelable(Method method, Class<? extends Parcelable> parcelableClass) {
        return isStatic(method.getModifiers())
                && method.getParameterTypes().length == 0
                && method.getReturnType() == parcelableClass;
    }

    private static <T extends Parcelable> ReparcelingResult<T> reparcel(Method method) {
        try {
            T original = (T) method.invoke(null);
            return Reparceler.reparcel(original, method.getName());
        } catch (Throwable error) {
            return new ReparcelingResult<>(null, null, false, method.getName(), error);
        }
    }
}
