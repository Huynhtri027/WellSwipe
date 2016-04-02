package com.well.swipe.tools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.provider.AlarmClock;
import android.provider.Settings;

import com.well.swipe.ItemSwipeTools;
import com.well.swipe.R;
import com.well.swipe.activitys.SwipeSettingActivity;
import com.well.swipe.view.AngleItemCommon;
import com.well.swipe.view.AngleItemStartUp;
import com.well.swipe.view.SwipeLayout;


/**
 * Created by mingwei on 3/27/16.
 */
public class ToolsStrategy {

    private volatile static ToolsStrategy mInstance;

    private ToolsStrategy() {
    }

    public static ToolsStrategy getInstance() {
        if (mInstance == null) {
            synchronized (ToolsStrategy.class) {
                if (mInstance == null) {
                    mInstance = new ToolsStrategy();
                }
            }
        }
        return mInstance;
    }

    public void initView(Context context, AngleItemCommon itemview, ItemSwipeTools item) {
        if (item.mAction.equals(context.getString(R.string.swipe_flash))) {
            /**
             * 手电筒
             */
            itemview.setItemIcon(FlashLight.getInstance().getDrawableState(context).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_wifi))) {
            /**
             *wifi
             */
            itemview.setItemIcon(WifiAndData.getWifiDrawableState(context).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_data))) {
            /**
             * data
             */
            itemview.setItemIcon(WifiAndData.getDataDrawableState(context).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_camere))) {
            /**
             * camera
             */
            itemview.setItemIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_camera)).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_flightmode))) {
            /**
             * airplane_mode
             */
            itemview.setItemIcon(FlightMode.getDrawableState(context).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_mute))) {
            /**
             * 静音
             */
            itemview.setItemIcon(SwipeAudio.getInstance(context).getDrawableState(context).getBitmap());
            itemview.setTitle(SwipeAudio.getInstance(context).getTitleState(context));
        } else if (item.mAction.equals(context.getString(R.string.swipe_autorotation))) {
            /**
             * 自动旋转屏幕
             */
            itemview.setItemIcon(SwipeRotation.getDrawableState(context).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_setting))) {
            /**
             * 系统设置
             */
            itemview.setItemIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_setting_system)).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_alarm))) {
            /**
             * 闹钟
             */
            itemview.setItemIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_alarmclock)).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_screenbrightness))) {
            /**
             * 屏幕亮度
             */
            itemview.setItemIcon(SwipeBrightness.getInstance(context).getDrawableState(context).getBitmap());
            itemview.setTitle(SwipeBrightness.getInstance(context).getTitleState(context));
        } else if (item.mAction.equals(context.getString(R.string.swipe_speeder))) {
            /**
             * 屏幕锁
             */
            itemview.setItemIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_clean_memory)).getBitmap());
            itemview.setTitle(String.valueOf(ClearMemory.getInstance().getAvailMemory(context)));
        } else if (item.mAction.equals(context.getString(R.string.swipe_screenlock))) {
            /**
             * 日历
             */
            itemview.setItemIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_client_hide_when_screen_off)).getBitmap());
            itemview.setTitle(LockTime.getInstance().getTitleState(context));
        } else if (item.mAction.equals(context.getString(R.string.swipe_calendar))) {
            /**
             * 计算器
             */
            itemview.setItemIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_calendar)).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_calculator))) {
            /**
             * 闹钟
             */
            itemview.setItemIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_calculator)).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_swipesetting))) {
            /**
             * swipe设置
             */
            itemview.setItemIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_assistant_touch_enable)).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_bluetooth))) {
            /**
             * 蓝牙
             */
            itemview.setItemIcon(SwipeBluetooth.getInstance().getDrawableState(context).getBitmap());
        }

    }

    /**
     * 点击事件匹配
     *
     * @param context
     * @param itemview     点击的view
     * @param item         view的数据
     * @param mSwipeLayout SwipeLayout
     */
    public void toolsClick(Context context, AngleItemStartUp itemview, ItemSwipeTools item, SwipeLayout mSwipeLayout) {
        if (item.mAction.equals(context.getString(R.string.swipe_flash))) {
            FlashLight.getInstance().onAndOff(context);
            itemview.setItemIcon(FlashLight.getInstance().getDrawableState(context).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_wifi))) {
            WifiAndData.setWifiEnable(context, !WifiAndData.isWifiEnable(context));
        } else if (item.mAction.equals(context.getString(R.string.swipe_data))) {
            if (Build.VERSION.SDK_INT >= 21) {
                launchDataUsageSettings(context);
            } else {
                WifiAndData.setMobileDataEnabled(context, !WifiAndData.isMobileDataEnable(context));
            }
        } else if (item.mAction.equals(context.getString(R.string.swipe_camere))) {
            launchCarmera(context);
            mSwipeLayout.dismissAnimator();
        } else if (item.mAction.equals(context.getString(R.string.swipe_flightmode))) {
            Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            mSwipeLayout.dismissAnimator();
        } else if (item.mAction.equals(context.getString(R.string.swipe_mute))) {
            SwipeAudio.getInstance(context).changeState();
            itemview.setItemIcon(SwipeAudio.getInstance(context).getDrawableState(context).getBitmap());
        } else if (item.mAction.equals(context.getString(R.string.swipe_autorotation))) {
            if (SwipeRotation.getRotationStatus(context) == 1) {
                SwipeRotation.setRotationStatus(context.getContentResolver(), 0);
            } else {
                SwipeRotation.setRotationStatus(context.getContentResolver(), 1);
            }
        } else if (item.mAction.equals(context.getString(R.string.swipe_setting))) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            mSwipeLayout.dismissAnimator();
        } else if (item.mAction.equals(context.getString(R.string.swipe_alarm))) {
            Intent alarmas = new Intent(AlarmClock.ACTION_SET_ALARM);
            alarmas.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(alarmas);
            mSwipeLayout.dismissAnimator();
        } else if (item.mAction.equals(context.getString(R.string.swipe_screenbrightness))) {
            SwipeBrightness.getInstance(context).setBrightStatus(context);
        } else if (item.mAction.equals(context.getString(R.string.swipe_speeder))) {
            ClearMemory.getInstance().cleanMemory(context);
        } else if (item.mAction.equals(context.getString(R.string.swipe_screenlock))) {
            LockTime.getInstance().changeState(context);
        } else if (item.mAction.equals(context.getString(R.string.swipe_calendar))) {
            try {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent((new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity")));
                context.startActivity(intent);
                mSwipeLayout.dismissAnimator();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (item.mAction.equals(context.getString(R.string.swipe_calculator))) {
            try {
                Intent intent = new Intent();
                intent.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                mSwipeLayout.dismissAnimator();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (item.mAction.equals(context.getString(R.string.swipe_swipesetting))) {

            Intent intent = new Intent(context, SwipeSettingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            mSwipeLayout.dismissAnimator();
        } else if (item.mAction.equals(context.getString(R.string.swipe_bluetooth))) {
            SwipeBluetooth.getInstance().changeState();
        }
    }

    private void launchDataUsageSettings(Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setComponent(new ComponentName("com.android.settings",
                    "com.android.settings.Settings$DataUsageSummaryActivity"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void launchCarmera(Context context) {
        try {
            Intent mIntent = new Intent("android.media.action.STILL_IMAGE_CAMERA");
            //mIntent.addCategory(Intent.CATEGORY_DEFAULT);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(mIntent);
        } catch (Exception e) {

        }
    }


}