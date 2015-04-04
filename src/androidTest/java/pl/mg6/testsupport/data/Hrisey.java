package pl.mg6.testsupport.data;

import hrisey.Parcelable;
import lombok.AllArgsConstructor;

@Parcelable
@AllArgsConstructor(suppressConstructorProperties = true)
public final class Hrisey implements android.os.Parcelable {

    private int id;
    private String key;
}
