package cn.ucai.superwechat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatApplication;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.bean.Gift;

/**
 * Created by wei on 2016/6/7.
 */
@RemoteViews.RemoteView
public class LiveLeftGiftView extends RelativeLayout {
    @BindView(R.id.avatar)
    EaseImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.gift_image)
    ImageView giftImage;

    int[] drawable = {R.drawable.hani_gift_1, R.drawable.hani_gift_2, R.drawable.hani_gift_3
            , R.drawable.hani_gift_4, R.drawable.hani_gift_5, R.drawable.hani_gift_6
            , R.drawable.hani_gift_7, R.drawable.hani_gift_8};
    @BindView(R.id.tvGiftName)
    TextView tvGiftName;

    public LiveLeftGiftView(Context context) {
        super(context);
        init(context, null);
    }

    public LiveLeftGiftView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public LiveLeftGiftView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_left_gift, this);
        ButterKnife.bind(this);
    }

    public void setName(String name) {
//        String nick = EaseUserUtils.getAppUserInfo(name).getMUserNick();
        this.name.setText(name);
    }

    public void setAvatar(String name) {
        EaseUserUtils.setAppUserAvatar(getContext(), name, this.avatar);
//        Glide.with(getContext()).load(avatar).into(this.avatar);
    }

    public void setGift(int id) {
        Gift gift = SuperWeChatHelper.getInstance().getAppGiftList().get(id);
        if (gift != null && id != 0) {
            tvGiftName.setText("送了一个" + gift.getGname());
            this.giftImage.setImageResource(getGiftImage(id));
        }
    }

    public ImageView getGiftImageView() {
        return giftImage;
    }

    private int getGiftImage(int id) {
        Context context = SuperWeChatApplication.getInstance().getApplicationContext();
        String name = "hani_gift_" + id;
        int resId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return resId;
    }
}
