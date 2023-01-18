package com.notorein.BasicAppII;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class CustomView extends View {
    private Canvas canvas;

    public CustomView(Context context) {
        super(context);
        // Initialize the canvas object
        canvas = new Canvas();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw the canvas onto the view
//        canvas.drawCanvas(this.canvas, 0, 0, null);
        canvas.drawCircle((0 )* 0.5f , 0 * 2.3f, 0, null);
    }
}