package cn.yan.appstartup;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import java.util.Collections;
import java.util.List;

/**
 * @author yanbo1
 */
public class Cinitializer implements Initializer<Object> {
    private static final String TAG = "Initializer";

    @NonNull
    @Override
    public Object create(@NonNull Context context) {
        Log.d(TAG, "Cinitializer create...");
        return new Object();
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        Log.d(TAG, "Cinitializer dependencies");
        return Collections.emptyList();
    }
}
