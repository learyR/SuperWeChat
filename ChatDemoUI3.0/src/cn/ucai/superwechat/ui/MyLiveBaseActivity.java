package cn.ucai.superwechat.ui;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
/**
 * Created by Lr on 2016/12/12.
 */

public class MyLiveBaseActivity extends AppCompatActivity{

    protected void showToast(final String toastContent){
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(MyLiveBaseActivity.this, toastContent, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void showLongToast(final String toastContent){
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(MyLiveBaseActivity.this, toastContent, Toast.LENGTH_LONG).show();
            }
        });
    }

}
