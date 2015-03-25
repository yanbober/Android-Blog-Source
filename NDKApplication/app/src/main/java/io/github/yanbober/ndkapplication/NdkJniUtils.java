package io.github.yanbober.ndkapplication;

public class NdkJniUtils {
    public native String generateKey(String name);

    static {
        System.loadLibrary("YanboberJniLibName");
    }
}
