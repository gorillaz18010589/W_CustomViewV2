package com.example.customviewv2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.annotation.Dimension;
import androidx.annotation.Nullable;

public class CustomRandomTextView extends androidx.appcompat.widget.AppCompatTextView {
    private String titleText;
    private int titleTextColor;
    private int titleTextSize;
    private String TAG = "hank";

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect rect;
    private Paint paint;

    public CustomRandomTextView(Context context) {
        super(context);
    }

    public CustomRandomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRandomTextView);
        int count = typedArray.getIndexCount();

        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomRandomTextView_titleText:
                    titleText = typedArray.getString(attr);
//                    setMyTextTitle();
                    Log.v(TAG, "CustomRandomTextView_titleText:");
                    break;
                case R.styleable.CustomRandomTextView_titleTextColor:
                    titleTextColor = typedArray.getColor(attr, Color.GREEN);
//                    setTextColor(titleTextColor);
                    Log.v(TAG, "CustomRandomTextView_titleTextColor:");
                    break;
                case R.styleable.CustomRandomTextView_titleTxtSize:
                    titleTextSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()
                    ));
                    Log.v(TAG, "CustomRandomTextView_titleTxtSize:");
                    break;
            }
        }
            typedArray.recycle();

        //獲得繪製文本給的寬跟高
        paint = new Paint();
        paint.setTextSize(titleTextSize);
        rect = new Rect();
        paint.getTextBounds(titleText,0,titleText.length(),rect);
        Log.v(TAG, "titleText:" + titleText + "titleText.length"+titleText.length());
    }

    public CustomRandomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getWidth(),getHeight(),paint);

        paint.setColor(titleTextColor); //畫筆設置顏色為你輸入的顏色
        canvas.drawText(titleText, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, paint);
        Log.v(TAG, "onDraw:");
    }

    //2.4: 自訂設定TextTitle方法
    private void setMyTextTitle() {
        //如果朕的值有人賦予了才可設定Title
        if (this.titleText != null) {
            setText(this.titleText);
            Log.v(TAG, "setMyTextTitle");
        }
    }

    private void setMyTextColor(int color){
            setTextColor(color);
    }



}
