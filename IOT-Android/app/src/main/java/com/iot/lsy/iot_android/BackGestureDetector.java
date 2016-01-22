package com.iot.lsy.iot_android;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by lsy on 16-1-22.
 */
public class BackGestureDetector implements GestureDetector.OnGestureListener {

    private Activity activity;

    public BackGestureDetector(Activity context) {
        this.activity = context;
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // 已经向右滑动(后－前)超过100px,并且上下滑动不超过60px
        if((e2.getX() - e1.getX()) > 100 && Math.abs(e1.getY() - e2.getY()) < 60) {
            activity.onBackPressed();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
