package cn.ucai.superwechat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hyphenate.easeui.domain.User;

import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.ui.AddContactActivity;
import cn.ucai.superwechat.ui.LoginActivity;
import cn.ucai.superwechat.ui.MainActivity;
import cn.ucai.superwechat.ui.PersonalProfileActivity;
import cn.ucai.superwechat.ui.RegisterActivity;
import cn.ucai.superwechat.ui.SettingsActivity;
import cn.ucai.superwechat.ui.UserProfileActivity;


public class MFGT {
    public static void finish(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void gotoMainActivity(Activity context){
        startActivity(context, MainActivity.class);
    }
    public static void startActivity(Activity context,Class<?> cls){
        Intent intent = new Intent();
        intent.setClass(context,cls);
        context.startActivity(intent);
    }
    public static void startActivity(Context context,Intent intent){
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public static void startActivityForResult(Activity context, Intent intent, int requestCode) {
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 跳转到登录Activity
     * @param context
     */
    public static void gotoLoginActivity(Activity context) {
        startActivity(context, LoginActivity.class);
    }

    /**
     * 跳转到注册Activity
     * @param context
     */
    public static void gotoRegisterActivity(Activity context) {
        startActivity(context, RegisterActivity.class);
    }
    /**
     * 跳转到设置
     * @param context
     */
    public static void gotoSettingActivity(Activity context) {
        startActivity(context, SettingsActivity.class);
    }

    /**
     * 跳转到个人资料Activity
     * @param context
     */
    public static void gotoUserProfileActivity(Activity context) {
        startActivity(context, UserProfileActivity.class);
    }
    /**
     * 跳转到个人资料Activity
     * @param context
     */
    public static void gotoAddContactActivity(Activity context) {
        startActivity(context, AddContactActivity.class);
    }
    public static void gotoPersonalProfileActivity(Activity context,User user) {
        Intent intent = new Intent();
        intent.setClass(context, PersonalProfileActivity.class);
        intent.putExtra(I.User.USER_NAME, user);
        startActivity(context, intent);
    }


}
