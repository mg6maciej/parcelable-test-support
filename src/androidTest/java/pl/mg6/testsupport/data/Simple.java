package pl.mg6.testsupport.data;

import android.os.Parcel;
import android.os.Parcelable;

public final class Simple implements Parcelable {

    private final int value;

    public Simple(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Simple simple = (Simple) o;
        return value == simple.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(value);
    }

    private Simple(Parcel in) {
        this.value = in.readInt();
    }

    public static final Parcelable.Creator<Simple> CREATOR = new Parcelable.Creator<Simple>() {
        public Simple createFromParcel(Parcel source) {
            return new Simple(source);
        }

        public Simple[] newArray(int size) {
            return new Simple[size];
        }
    };
}
