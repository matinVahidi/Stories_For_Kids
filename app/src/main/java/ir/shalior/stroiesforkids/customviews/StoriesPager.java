package ir.shalior.stroiesforkids.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

public class StoriesPager extends ViewPager {
    private boolean isPagingEnabled = false;

    public StoriesPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewCompat.setLayoutDirection(this, ViewCompat.LAYOUT_DIRECTION_RTL);
        initMyScroller();
    }

    public void setLastItemAsDefault() {
        setCurrentItem(getAdapter().getCount() - 1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }

    private void initMyScroller() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this, new MyScroller(getContext())); // my liner scroller

            Field mFlingDistance = viewpager.getDeclaredField("mFlingDistance");
            mFlingDistance.setAccessible(true);
            mFlingDistance.set(this, 10);//10 dip

            Field mMinimumVelocity = viewpager.getDeclaredField("mMinimumVelocity");
            mMinimumVelocity.setAccessible(true);
            mMinimumVelocity.set(this, 0); //0 velocity

        } catch (Exception e) {
            Log.i("Vpager err", "smt Wrong!");
        }

    }

    public class MyScroller extends Scroller {
        public MyScroller(Context context) {
            super(context, new LinearInterpolator()); // my LinearInterpolator
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, 400);//175 duration
        }
    }

}
