package ir.shalior.stroiesforkids.model.myobjectbox;

import android.content.Context;

import io.objectbox.BoxStore;
import ir.shalior.stroiesforkids.model.MyObjectBox;

public class ObjectBox {
    private static BoxStore boxStore;

    public static void init(Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
    }

    public static BoxStore get() { return boxStore; }
}