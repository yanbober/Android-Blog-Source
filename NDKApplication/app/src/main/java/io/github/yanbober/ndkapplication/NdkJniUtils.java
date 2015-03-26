package io.github.yanbober.ndkapplication;

public class NdkJniUtils {
    public native String nativeGenerateKey(String name);

    static {
        System.loadLibrary("YanboberJniLibName");
    }
}
