package ir.shalior.stroiesforkids;

import android.app.Application;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import ir.shalior.stroiesforkids.model.myobjectbox.ObjectBox;

public class StoriesForKidsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.init(this);

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/koodak.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }
}