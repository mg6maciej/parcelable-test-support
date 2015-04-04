package pl.mg6.testsupport;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Field;

public class Reparceler {

    public <T extends Parcelable> ReparcelingResult<T> reparcel(T original) {
        return reparcel(original, null);
    }

    static <T extends Parcelable> ReparcelingResult<T> reparcel(T original, String methodName) {
        try {
            T reparceled = reparcelImpl(original);
            boolean areEqual = original.equals(reparceled);
            return new ReparcelingResult<>(original, reparceled, areEqual, methodName, null);
        } catch (Throwable error) {
            return new ReparcelingResult<>(original, null, false, methodName, createError(original, error));
        }
    }

    private static <T extends Parcelable> T reparcelImpl(T original) throws Throwable {
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Field creatorField = original.getClass().getField("CREATOR");
        Parcelable.Creator<T> creator = (Parcelable.Creator<T>) creatorField.get(null);
        return creator.createFromParcel(parcel);
    }

    private static ReparcelingError createError(Parcelable original, Throwable error) {
        String name = original.getClass().getSimpleName();
        return new ReparcelingError(String.format("Missing public static CREATOR field on class %s.", name), error);
    }
}
