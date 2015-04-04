package pl.mg6.testsupport.data;

import org.parceler.Parcel;

@Parcel
public class Parceler {

    long id;
    String name;

    public Parceler() {
    }

    public Parceler(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
