package cn.yan.appstartup;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yanbo1
 */
public class DasAinitializer extends Ainitializer {
    private static final String TAG = "Initializer";

    @NonNull
    @Override
    public Object create(@NonNull Context context) {
        Log.d(TAG, "DasAinitializer create...");
        return super.create(context);
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        Log.d(TAG, "DasAinitializer dependencies");
        return Arrays.asList(Binitializer.class);
    }
}
