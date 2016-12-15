package cn.ucai.superwechat.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.bean.Gift;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.data.NetDao;
import cn.ucai.superwechat.data.OkHttpUtils;
import cn.ucai.superwechat.utils.ResultUtils;

/**
 * Created by wei on 2016/7/25.
 */
public class GiftListDialog extends DialogFragment {

    Unbinder unbinder;
    @BindView(R.id.rvGift)
    RecyclerView rvGift;
    @BindView(R.id.tvPay)
    TextView tvPay;
    @BindView(R.id.tvWallet)
    TextView tvWallet;

    GridLayoutManager mLayoutManager;
    List<Gift> giftList = new ArrayList<>();
    GiftAdapter mAdapter;
    View.OnClickListener mOnClickListener;
    int[] drawable = {R.drawable.hani_gift_1, R.drawable.hani_gift_2, R.drawable.hani_gift_3
            , R.drawable.hani_gift_4, R.drawable.hani_gift_5, R.drawable.hani_gift_6
            , R.drawable.hani_gift_7, R.drawable.hani_gift_8};
    public static GiftListDialog newInstance() {
        GiftListDialog dialog = new GiftListDialog();
        return dialog;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gifts_list_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mLayoutManager = new GridLayoutManager(getContext(), 4);
        rvGift.setLayoutManager(mLayoutManager);
        mAdapter = new GiftAdapter(getContext(), giftList);
        rvGift.setAdapter(mAdapter);
    }

    private void initData() {
        Map<Integer, Gift> appGiftList = SuperWeChatHelper.getInstance().getAppGiftList();
        if (appGiftList != null && appGiftList.size() > 0) {
            List<Gift> giftList = new ArrayList<>();
            for (Gift gift : appGiftList.values()) {
//                Log.e("leary", gift.toString());
                giftList.add(gift);
            }
            mAdapter.initData(giftList);
        } else {
            NetDao.downGifts(getContext(), new OkHttpUtils.OnCompleteListener<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result != null) {
                        Result giftList = ResultUtils.getListResultFromJson(result, Gift.class);
                        if (giftList!= null && giftList.isRetMsg()) {
                            List<Gift> list= (List<Gift>) giftList.getRetData();
                            if (list != null && list.size() > 0) {
                                mAdapter.initData(list);
                                SuperWeChatHelper.getInstance().updateAppGiftList(list);
                            }
                        }
                    }
                }

                @Override
                public void onError(String error) {

                }
            });
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    class GiftViewHolder extends RecyclerView.ViewHolder{
        ImageView ivGiftPic;
        TextView tvGiftPrice, tvGiftName;
        View view;
        public GiftViewHolder(View itemView) {
            super(itemView);
            ivGiftPic = (ImageView) itemView.findViewById(R.id.ivGiftPic);
            tvGiftName = (TextView) itemView.findViewById(R.id.tvGiftName);
            tvGiftPrice = (TextView) itemView.findViewById(R.id.tvGiftPrice);
            view = itemView.findViewById(R.id.layout_gift);
            view.setOnClickListener(mOnClickListener);
        }
    }
    class GiftAdapter extends RecyclerView.Adapter<GiftViewHolder>{
        Context context;
        List<Gift> giftList;
        public GiftAdapter(Context context, List<Gift> giftList) {
            this.context = context;
            this.giftList = giftList;
            mOnClickListener=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id= (int) v.getTag();
                    if (dialogListener != null) {
                        dialogListener.onMentionClick(id);
                    }
                }
            };
        }

        @Override
        public GiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View layout = LayoutInflater.from(context).inflate(R.layout.gift_layout, parent, false);
            GiftViewHolder holder = new GiftViewHolder(layout);
            return holder;
        }

        @Override
        public void onBindViewHolder(GiftViewHolder holder, int position) {
            Gift gift = giftList.get(position);
            holder.ivGiftPic.setImageResource(drawable[position]);
            holder.tvGiftPrice.setText(String.valueOf(gift.getGprice())+"￥");
            holder.tvGiftName.setText(gift.getGname());
//            EaseUserUtils.setAppUserPathAvatar(context, gift.getGurl(), holder.ivGiftPic);
            holder.itemView.setTag(gift.getId());
        }

        @Override
        public int getItemCount() {
            return giftList.size();
        }

        public void initData(List<Gift> list) {
            if (giftList != null) {
                giftList.clear();
            }
            giftList.addAll(list);
            notifyDataSetChanged();
        }
    }
    private GiftListDialog.GiftDialogListener dialogListener;

    public void setGiftDialogListener(GiftListDialog.GiftDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    interface GiftDialogListener {
        void onMentionClick(int id);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带theme的构造器，获得的dialog边框距离屏幕仍有几毫米的缝隙。
        // Dialog dialog = new Dialog(getActivity());
        Dialog dialog = new Dialog(getActivity(), R.style.room_user_details_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.fragment_room_user_details);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
