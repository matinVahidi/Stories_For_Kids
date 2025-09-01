package ir.shalior.stroiesforkids.games.tapouniom.engine;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;

public class FlexConfig {
    private final Point displaySize;
    private final float scale;

    public FlexConfig(Activity activity, int baseDisplayWidth) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        displaySize = new Point(metrics.widthPixels, metrics.heightPixels);
        scale = (float) displaySize.x / baseDisplayWidth;
    }

    public int getDisplayWidth() {
        return displaySize.x;
    }

    public int getDisplayHeight() {
        return displaySize.y;
    }

    public float getScale() {
        return scale;
    }
}