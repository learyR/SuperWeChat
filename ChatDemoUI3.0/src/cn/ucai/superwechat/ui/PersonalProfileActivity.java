package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        ButterKnife.bind(this);
        user= (User) getIntent().getSerializableExtra(I.User.USER_NAME);
        if (user == null) {
            finish();
        }
        initView();

    }

    private void initView() {
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(getString(R.string.userinfo_txt_profile));
        setUserInfo();
    }
    private void setUserInfo() {
        EaseUserUtils.setAppUserAvatar(this, user.getMUserName(), ivAvatar);
        EaseUserUtils.setAppUserNick(user.getMUserName(), tvUserInfoNick);
        EaseUserUtils.setAppUserNameWithNo(user.getMUserName(), tvUserName);
    }

    @OnClick(R.id.img_back)
    public void onClick() {
    }
}
