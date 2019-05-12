package cn.bsd.learn.discrollve.sample;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        //采花大盗---childview里面的自定义属性--->MyFrameLayout
        return new MyLayoutParams(getContext(), attrs);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        //在child view外面包裹一层容器 ---- 偷梁换柱
        MyLayoutParams p = (MyLayoutParams) params;

        if(!isDiscrollvable(p)){//判断如果没有设置自定义属性，就不用包裹一层MyFrameLayout
            super.addView(child, index, params);
        } else{
            MyFrameLayout mf = new MyFrameLayout(getContext(),null);
            mf.setmDiscrollveAlpha(p.mDiscrollveAlpha);
            mf.setmDiscrollveFromBgColor(p.mDiscrollveFromBgColor);
            mf.setmDiscrollveToBgColor(p.mDiscrollveToBgColor);
            mf.setmDiscrollveScaleX(p.mDiscrollveScaleX);
            mf.setmDiscrollveScaleY(p.mDiscrollveScaleY);
            mf.setmDisCrollveTranslation(p.mDiscrollveTranslation);

            mf.addView(child);

            super.addView(mf, index, params);
        }

    }

    private boolean isDiscrollvable(MyLayoutParams p){
        return p.mDiscrollveAlpha||
                p.mDiscrollveScaleX ||
                p.mDiscrollveScaleY||
                p.mDiscrollveTranslation!=-1||
                (p.mDiscrollveFromBgColor!=-1&&p.mDiscrollveToBgColor!=-1);
    }

    private class MyLayoutParams extends LinearLayout.LayoutParams {
        private final boolean mDiscrollveAlpha;//是否需要透明度动画
        private final boolean mDiscrollveScaleX;//是否需要x轴方向缩放
        private final boolean mDiscrollveScaleY;//是否需要y轴方向缩放
        private final int mDiscrollveTranslation;//平移值
        private final int mDiscrollveFromBgColor;//背景颜色变化开始值
        private final int mDiscrollveToBgColor;//背景颜色变化结束值

        public MyLayoutParams(Context context, AttributeSet attrs) {
            super(context,attrs);
            //获取自定义属性
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DiscrollView_LayoutParams);
            mDiscrollveAlpha = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_alpha,false);
            mDiscrollveScaleX = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleX,false);
            mDiscrollveScaleY = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleY,false);
            mDiscrollveTranslation = a.getInt(R.styleable.DiscrollView_LayoutParams_discrollve_translation,-1);
            mDiscrollveFromBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_fromBgColor,-1);
            mDiscrollveToBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_toBgColor,-1);
            a.recycle();
        }
    }
}
