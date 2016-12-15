package cn.ucai.superwechat.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.bean.Wallet;
import cn.ucai.superwechat.data.NetDao;
import cn.ucai.superwechat.data.OkHttpUtils;
import cn.ucai.superwechat.utils.ResultUtils;

public class RechargeActivity extends BaseActivity {

    @BindView(R.id.et_recharge_amount)
    EditText etRechargeAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_recharge)
    public void onClick() {
        final String rmb = etRechargeAmount.getText().toString().trim();
        final int change = Integer.parseInt(SuperWeChatHelper.getInstance().getCurrentUsernChange());
        if (!rmb.equals("")) {
            NetDao.rechargeMoney(this, EMClient.getInstance().getCurrentUser(), rmb, new OkHttpUtils.OnCompleteListener<String>() {
                @Override
                public void onSuccess(String s) {
                    if (s != null) {
                        Result result = ResultUtils.getResultFromJson(s, Wallet.class);
                        if (result != null && result.isRetMsg()) {
                            int reRmb = Integer.parseInt(rmb);
//                            Wallet wallet = (Wallet) result.getRetData();
                            SuperWeChatHelper.getInstance().setCurrentUserChange(String.valueOf(reRmb+ change));
                            AlertDialog.Builder builder = new AlertDialog.Builder(RechargeActivity.this);
                            builder.setTitle("提示");
                            builder.setMessage("充值余额成功");
                            builder.setPositiveButton("继续充值", null);
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                            builder.create().show();
                        }
                    }
                }
                @Override
                public void onError(String error) {
                    Toast.makeText(RechargeActivity.this, "充值余额失败", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(RechargeActivity.this, "请输入充值金额", Toast.LENGTH_SHORT).show();
        }
    }
}
