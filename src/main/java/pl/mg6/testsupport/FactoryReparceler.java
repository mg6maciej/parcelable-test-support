package pl.mg6.testsupport;

import android.os.Parcelable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Modifier.isStatic;

public class FactoryReparceler {

    public <T extends Parcelable> ReparcelingResultList<T> reparcel(Class<?> factoryClass) {
        List<ReparcelingResult<T>> results = new ArrayList<>();
        for (Method method : factoryClass.getMethods()) {
            if (staticNoParamsReturningParcelable(method)) {
                ReparcelingResult<T> result = reparcel(method);
                results.add(result);
            }
        }
        return new ReparcelingResultList<>(results);
    }

    private static boolean staticNoParamsReturningParcelable(Method method) {
        return isStatic(method.getModifiers())
                && method.getParameterTypes().length == 0
                && Parcelable.class.isAssignableFrom(method.getReturnType());
    }

    private static <T extends Parcelable> ReparcelingResult<T> reparcel(Method method) {
        try {
            T original = (T) method.invoke(null);
            return Reparceler.reparcel(original, method.getName());
        } catch (Throwable error) {
            return new ReparcelingResult<>(null, null, false, method.getName(), createError(method, error));
        }
    }

    private static ReparcelingError createError(Method method, Throwable error) {
        String methodName = method.getName();
        String className = method.getDeclaringClass().getSimpleName();
        String message = String.format("Cannot invoke method %s on class %s.", methodName, className);
        return new ReparcelingError(message, error);
    }
}
