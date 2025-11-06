package com.onestop;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import com.onestop.R;
import com.onestop.BuildConfig;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.widget.Button;
import androidx.core.content.FileProvider;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ToggleView extends View {
    public interface OnToggleChanged { void onChanged(boolean on); }

    private final Paint trackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint knobPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF trackRect = new RectF();
    private boolean checked = false;
    private OnToggleChanged listener;

    public ToggleView(Context context) { super(context); init(); }
    public ToggleView(Context context, @Nullable AttributeSet attrs) { super(context, attrs); init(); }
    public ToggleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        setClickable(true);
        trackPaint.setStyle(Paint.Style.FILL);
        trackPaint.setColor(0xFF000000);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(0xFFFFFFFF);
        borderPaint.setStrokeWidth(8f);
        knobPaint.setStyle(Paint.Style.FILL);
        knobPaint.setColor(0xFFD50000);
        setContentDescription("OFF");
    }

    public void setOnToggleChanged(OnToggleChanged l) { this.listener = l; }
    public boolean isChecked() { return checked; }
    public void setChecked(boolean value) {
        checked = value;
        knobPaint.setColor(checked ? 0xFF00C853 : 0xFFD50000);
        setContentDescription(checked ? "ON" : "OFF");
        invalidate();
        if (listener != null) listener.onChanged(checked);
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        trackRect.set(8, 8, w - 8, h - 8);
        borderPaint.setStrokeWidth(Math.max(6f, Math.min(w, h) * 0.02f));
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = trackRect.height() / 2f;
        canvas.drawRoundRect(trackRect, radius, radius, trackPaint);
        canvas.drawRoundRect(trackRect, radius, radius, borderPaint);
        float knobRadius = radius * 0.8f;
        float cx = checked ? (trackRect.right - radius) : (trackRect.left + radius);
        float cy = trackRect.centerY();
        canvas.drawCircle(cx, cy, knobRadius, knobPaint);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            setChecked(!checked);
            performClick();
            return true;
        }
        return super.onTouchEvent(event);
    }
    @Override public boolean performClick() { return super.performClick(); }
}