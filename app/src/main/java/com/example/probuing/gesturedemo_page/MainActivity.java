package com.example.probuing.gesturedemo_page;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class MainActivity extends ActionBarActivity implements GestureDetector.OnGestureListener
{

    private ViewFlipper flipper;
    private GestureDetector detector;
    //动画数组
    Animation[] animation = new Animation[4];
    //手势两点之间的最小距离
    private final int FLIP_DISTANCE = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flipper = ((ViewFlipper) findViewById(R.id.flipper));
        //手势检测器
        detector = new GestureDetector(this,this);
        //为ViewFlipper添加ImageView组件
        flipper.addView(addImageView(R.mipmap.ic_launcher));
        flipper.addView(addImageView(R.mipmap.ic_launcher));
        flipper.addView(addImageView(R.mipmap.ic_launcher));
        flipper.addView(addImageView(R.mipmap.ic_launcher));
        flipper.addView(addImageView(R.mipmap.ic_launcher));
        flipper.addView(addImageView(R.mipmap.ic_launcher));
        //初始化Animation数组,加载动画
        animation[0] = AnimationUtils.loadAnimation(this,R.anim.left_in);
        animation[1] = AnimationUtils.loadAnimation(this,R.anim.left_out);
        animation[2] = AnimationUtils.loadAnimation(this,R.anim.right_in);
        animation[3] = AnimationUtils.loadAnimation(this,R.anim.right_out);
    }

    /**
     * 添加组件方法
     * @param resId
     * @return
     */
    private View addImageView(int resId)
    {
        ImageView imageView = new ImageView(this);
        //设置图片
        imageView.setImageResource(resId);
        //设置显示位置
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return imageView;
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
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        /*
           第一个触点事件的x坐标大于第二个触点事件x坐标超过常量——从右向左滑动
         */
        if (e1.getX()-e2.getX()>FLIP_DISTANCE)
        {
            //设置动画效果
            //进
            flipper.setAnimation(animation[0]);
            //出
            flipper.setAnimation(animation[1]);
            //显示下一个View
            flipper.showPrevious();
            return true;
        }
        /*
        如果第二个触点事件的X坐标大于第一个触点事件的x坐标超过常量——从右向左
         */
        else if(e2.getX()-e1.getX()>FLIP_DISTANCE)
        {
            flipper.setAnimation(animation[2]);
            flipper.setAnimation(animation[3]);
            flipper.showNext();
            return true;

        }
        return false;
    }
}
