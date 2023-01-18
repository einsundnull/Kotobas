package com.notorein.BasicAppII;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

public class MyCanvas extends View {


    private final Paint paint;
    private float displayWidth;
    private float displayHeight;

    public MyCanvas(Activity activity, Context context) {
        super(context);
        paint = new Paint();
        getDisplayMetrics(activity);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        int radius = (int) (displayWidth * 0.24);
        canvas.drawCircle((displayWidth )* 0.5f , radius * 2.3f, radius, paint);
    }

    private void getDisplayMetrics(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        displayHeight = displaymetrics.heightPixels;
        displayWidth = displaymetrics.widthPixels;
    }
}
