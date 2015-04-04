package pl.mg6.testsupport.data;

import android.os.Parcel;
import android.os.Parcelable;

public class WithNonStaticCreator implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public final Creator<WithNonStaticCreator> CREATOR = new Creator<WithNonStaticCreator>() {
        public WithNonStaticCreator createFromParcel(Parcel source) {
            return new WithNonStaticCreator();
        }

        public WithNonStaticCreator[] newArray(int size) {
            return new WithNonStaticCreator[size];
        }
    };
}
