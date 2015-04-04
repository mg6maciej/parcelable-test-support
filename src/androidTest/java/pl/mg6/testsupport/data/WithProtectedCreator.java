package pl.mg6.testsupport.data;

import android.os.Parcel;
import android.os.Parcelable;

public class WithProtectedCreator implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected static final Creator<WithProtectedCreator> CREATOR = new Creator<WithProtectedCreator>() {
        public WithProtectedCreator createFromParcel(Parcel source) {
            return new WithProtectedCreator();
        }

        public WithProtectedCreator[] newArray(int size) {
            return new WithProtectedCreator[size];
        }
    };
}
