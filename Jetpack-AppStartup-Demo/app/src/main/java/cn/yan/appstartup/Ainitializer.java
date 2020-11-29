package cn.yan.appstartup;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import java.util.Arrays;
import java.util.List;

/**
 * @author yanbo1
 */
public class Ainitializer implements Initializer<Object> {
    private static final String TAG = "Initializer";

    @NonNull
    @Override
    public Object create(@NonNull Context context) {
        Log.d(TAG, "Ainitializer create...");
        return new Object();
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        Log.d(TAG, "Ainitializer dependencies");

        return Arrays.asList(Cinitializer.class);
    }
}
