# Parcelable Test Support

Small library to help with testing Parcelable classes.

Testing `Parcelable` classes is simple. You just need to write your object to `Parcel` and then recreate it.

It looks like this:

```
MyUberParcelable original = ...
Parcel parcel = Parcel.obtain();
original.writeToParcel(parcel, 0);
parcel.setDataPosition(0);
MyUberParcelable reparceled = MyUberParcelable.CREATOR.createFromParcel(parcel);
// assert that reparceled is equal to original
```

## So why would I need a library for this?

First of all, the code above is boilerplate. And if you are like me, you might not like writing boilerplate.

Next thing, assertion code is omitted in this example. You would have to provide `equals` implementation.
For simple objects, it's not a problem, but what if you have a `Map<String, List<OtherParcelable>`? Moar boilerplate!
And what if you already have `equals` implementation used in production ocde that is only comparing on `id` field?
Reflection would be your friend. Fortunatelly there are libraries using reflection to check for equality.

This library uses reflection to check for equality for you.

Now if you already have in your `test` or `androidTest` directory factory classes, which are used by API stubs for functional testing,
you may think of not creating new objects and instead using objects returned from these factories.

Let's look at simple example of such factory:

```
public class SimpleFactory {

    public static Simple withZero() {
        return new Simple(0);
    }

    public static Simple withPerfectNumber() {
        return new Simple(6);
    }

    public static Simple withMaxValue() {
        return new Simple(Integer.MAX_VALUE);
    }
}
```

To reuse them in `Parcelable` implementation tests, you would have to call each method, save returned object in a list
and then loop over them with the code at the top of this page. Sounds like boilerplate? Bleh!

Not to mention, when someone adds new function here, it won't be used in tests. Again reflection would be your friend.

This library has all this: hidden boilerplate and reflection, so you don't have to write or see it again.

## OK, I'm in. What's the API?

When you want to test a single object:

```
// given
Simple simple = new Simple(6);
// when
ReparcelingResult<Simple> result = new Reparceler().reparcel(simple);
// then
assertTrue(result.areEqual());
```

When you have a factory like the `SimpleFactory` above:

```
// when
ReparcelingResultList<Simple> resultList = new FactoryReparceler().reparcel(SimpleFactory.class);
// then
assertTrue(resultList.areAllEqual());
```

Want more? Check [tests](https://github.com/mg6maciej/parcelable-test-support/tree/master/src/androidTest/java/pl/mg6/testsupport).

### Assertion failed. What's next?

`ReparcelingResult` has all the useful information to find out the cause of the issue:
* original `Parcelable`; might be useful if you use `FactoryReparceler` and not creating objects on your own,
* reparceled `Parcelable`; always useful to see the differences,
* method name; in case when `FactoryReparceler` is used, you know where to look,
* and error; for when parceling totally failed, e.g. `CREATOR` field is missing, `writeToParcel` or `createFromParcel` thrown an exception.

`ReparcelingResultList` allows you to get a subset of `ReparcelingResult`s where something wrong happened:

```
for (ReparcelingResult<Simple> issue : resultList.getIssues()) {
    String methodName = issue.getMethodName();
    // ...
}
```

## How do I add it to my project?

```
dependencies {
    androidTestCompile 'TODO: put it somewhere'
}
```

## License

Parcelable Test Support is a free software and may be used under the terms specified in the [LICENSE](LICENSE) file.
