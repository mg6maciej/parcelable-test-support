package pl.mg6.testsupport;

import android.os.Parcel;
import android.os.Parcelable;

public class Reparceler {

    public <T extends Parcelable> ReparcelResult<T> reparcel(T original) {
        return reparcel(original, null);
    }

    <T extends Parcelable> ReparcelResult<T> reparcel(T original, String methodName) {
        try {
            Parcel parcel = Parcel.obtain();
            original.writeToParcel(parcel, 0);
            parcel.setDataPosition(0);
            Parcelable.Creator<T> creator = (Parcelable.Creator<T>) original.getClass().getField("CREATOR").get(null);
            T reparceled = creator.createFromParcel(parcel);
            boolean areEqual = original.equals(reparceled);
            return new ReparcelResult<>(original, reparceled, areEqual, methodName, null);
        } catch (Throwable error) {
            return new ReparcelResult<>(original, null, false, methodName, error);
        }
    }
}
