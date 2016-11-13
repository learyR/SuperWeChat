package cn.ucai.superwechat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.data.NetDao;
import cn.ucai.superwechat.data.OkHttpUtils;
import cn.ucai.superwechat.utils.MFGT;
import cn.ucai.superwechat.utils.ResultUtils;

public class PersonalProfileActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tvUserInfoNick)
    TextView tvUserInfoNick;
    User user;
    String username;
    boolean isFriend;

    @BindView(R.id.btn_sendMsg)
    Button btnSend;
    @BindView(R.id.btn_addFriend)
    Button btnAddFriend;
    @BindView(R.id.btn_chat)
    Button btnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        ButterKnife.bind(this);
        username = getIntent().getStringExtra(I.User.USER_NAME);
        if (username == null) {
            finish();
            return;
        }
        initView();
        user = SuperWeChatHelper.getInstance().getAppContactList().get(username);
        if (user == null) {
            syncUserInfo();
            isFriend = false;
        } else {
            setUserInfo();
            isFriend = true;
        }
        isFriend(isFriend);
    }

    private void syncUserInfo() {
        NetDao.syncUserInfo(this, username, new OkHttpUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if (s != null) {
                    Result result = ResultUtils.getResultFromJson(s, User.class);
                    if (result == null & result.isRetMsg()) {
                        user = (User) result.getRetData();
                        if (user != null) {
                            setUserInfo();
                            if (isFriend) {
                                SuperWeChatHelper.getInstance().saveAppContact(user);
                            }
                        }
                    }
                }

            }

            @Override
            public void onError(String error) {
                MFGT.finish(PersonalProfileActivity.this);
                return;
            }
        });
    }

    private void initView() {
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(getString(R.string.userinfo_txt_profile));

    }

    private void isFriend(boolean isFriend) {
        if (isFriend) {
            btnSend.setVisibility(View.VISIBLE);
            btnChat.setVisibility(View.VISIBLE);
        } else {
            btnAddFriend.setVisibility(View.VISIBLE);
        }

    }

    private void setUserInfo() {
        EaseUserUtils.setAppUserAvatar(this, user.getMUserName(), ivAvatar);
        EaseUserUtils.setAppUserNick(user.getMUserNick(), tvUserInfoNick);
        EaseUserUtils.setAppUserNameWithNo(user.getMUserName(), tvUserName);
    }


    @OnClick({R.id.img_back, R.id.btn_sendMsg, R.id.btn_addFriend, R.id.btn_chat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MFGT.finish(this);
                break;
            case R.id.btn_sendMsg:
                MFGT.gotoChatActivity(this,user.getMUserName());
                break;
            case R.id.btn_addFriend:
                MFGT.gotoAddFriendActivity(this,user.getMUserName());
                break;
            case R.id.btn_chat:
                if (!EMClient.getInstance().isConnected())
                    Toast.makeText(this, R.string.not_connect_to_server, Toast.LENGTH_SHORT).show();
                else {
                    startActivity(new Intent(this, VideoCallActivity.class).putExtra("username", user.getMUserName())
                            .putExtra("isComingCall", false));
                    // videoCallBtn.setEnabled(false);
//                    inputMenu.hideExtendMenuContainer();
                }
                break;
        }
    }
}
