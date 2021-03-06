package pl.mg6.testsupport.data;

import android.os.Parcel;
import android.os.Parcelable;

public final class Simple implements Parcelable {

    private final int value;

    public Simple(int value) {
        this.value = value;
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

    public static final Creator<Simple> CREATOR = new Creator<Simple>() {
        public Simple createFromParcel(Parcel source) {
            return new Simple(source);
        }

        public Simple[] newArray(int size) {
            return new Simple[size];
        }
    };
}
