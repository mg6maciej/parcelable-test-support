package pl.mg6.testsupport.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public final class Complex implements Parcelable {

    private final Simple simple;
    private final Hrisey hrisey;
    public final List<String> list;

    public Complex(Simple simple, Hrisey hrisey, List<String> list) {
        this.simple = simple;
        this.hrisey = hrisey;
        this.list = list;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.simple, flags);
        dest.writeParcelable(this.hrisey, flags);
        if (this.list == null) {
            dest.writeInt(-1);
        } else {
            dest.writeInt(this.list.size());
            for (String value : this.list) {
                dest.writeString(value);
            }
        }
    }

    private Complex(Parcel in) {
        this.simple = in.readParcelable(Simple.class.getClassLoader());
        this.hrisey = in.readParcelable(Hrisey.class.getClassLoader());
        int size = in.readInt();
        if (size == -1) {
            this.list = null;
        } else {
            this.list = new ArrayList<>();
            while (size > 0) {
                this.list.add(in.readString());
                size--;
            }
        }
    }

    public static final Parcelable.Creator<Complex> CREATOR = new Parcelable.Creator<Complex>() {
        public Complex createFromParcel(Parcel source) {
            return new Complex(source);
        }

        public Complex[] newArray(int size) {
            return new Complex[size];
        }
    };
}
