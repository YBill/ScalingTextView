package com.example.bill.scalingtextview;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Bill on 2017/4/6.
 */

public class ExpandableTextView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private TextView contentText;
    private TextView scalingText;

    private static final int CONTENT_DESC_MAX_LINE = 3;// 默认展示最大行数3行
    private static final int SHRINK_UP_STATE = 1;// 收起状态
    private static final int SPREAD_STATE = 2;// 展开状态
    private int mState;

    public ExpandableTextView(Context context) {
        super(context);
        init(context);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.layout_expandable_textview, null);
        addView(view);
        contentText = (TextView) view.findViewById(R.id.tv_content);
        scalingText = (TextView) view.findViewById(R.id.tv_scaling);
        scalingText.setOnClickListener(this);
    }

    public void setText(String text) {
        contentText.setMaxLines(Integer.MAX_VALUE);
        contentText.setText(text);
        contentText.post(new Runnable() {
            @Override
            public void run() {
                int contentLine = contentText.getLineCount();
                if (contentLine > CONTENT_DESC_MAX_LINE) {
                    scalingText.setVisibility(VISIBLE);
                    putAway();
                } else {
                    scalingText.setVisibility(GONE);
                }
            }
        });
    }

    public void setTextSize(float size) {
        contentText.setTextSize(size);
    }

    public void setTextColor(@ColorRes int id) {
        contentText.setTextColor(context.getResources().getColor(id));
    }

    /**
     * 收起
     */
    private void putAway() {
        contentText.setMaxLines(CONTENT_DESC_MAX_LINE);
        contentText.requestLayout();
        scalingText.setText("显示全部");
        mState = SHRINK_UP_STATE;
    }

    /**
     * 展开
     */
    private void unFold() {
        contentText.setMaxLines(Integer.MAX_VALUE);
        contentText.requestLayout();
        scalingText.setText("收起");
        mState = SPREAD_STATE;
    }

    @Override
    public void onClick(View v) {
        if (v == scalingText) {
            if (mState == SPREAD_STATE) {
                putAway();
            } else if (mState == SHRINK_UP_STATE) {
                unFold();
            }
        }
    }

}
