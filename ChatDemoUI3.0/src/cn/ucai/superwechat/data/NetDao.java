package cn.ucai.superwechat.data;

import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;

import java.io.File;

import cn.ucai.superwechat.I;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.utils.L;
import cn.ucai.superwechat.utils.MD5;


public class NetDao {

    /**
     * 注册账号
     * @param context
     * @param userName
     * @param nickName
     * @param password
     * @param listener
     */
    public static void register(Context context, String userName, String nickName, String password, OkHttpUtils.OnCompleteListener<Result> listener) {
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_REGISTER)
                .addParam(I.User.USER_NAME, userName)
                .addParam(I.User.NICK, nickName)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(Result.class)
                .post()
                .execute(listener);
    }

    /**
     * 取消注册
     * @param context
     * @param userName
     * @param listener
     */
    public static void unregister(Context context, String userName, OkHttpUtils.OnCompleteListener<Result> listener) {
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UNREGISTER)
                .addParam(I.User.USER_NAME, userName)
                .targetClass(Result.class)
                .execute(listener);
    }

    /**
     * 用户登录
     * @param context
     * @param userName
     * @param password
     * @param listener
     */
    public static void login(Context context, String userName, String password, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME, userName)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 更新用户昵称
     * @param context
     * @param userName
     * @param nick
     * @param listener
     */
    public static void updateNick(Context context, String userName, String nick, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                .addParam(I.User.USER_NAME, userName)
                .addParam(I.User.NICK, nick)
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 跟新用户头像
     * @param context
     * @param userName
     * @param file
     * @param listener
     */
    public static void updateAvatar(Context context, String userName, File file, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                .addParam(I.NAME_OR_HXID, userName)
                .addParam(I.AVATAR_TYPE,I.AVATAR_TYPE_USER_PATH)
                .addFile2(file)
                .targetClass(String.class)
                .post()
                .execute(listener);
    }

    /**
     * 更新用户信息
     * @param context
     * @param userName
     * @param listener
     */
    public static void syncUserInfo(Context context, String userName, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME, userName)
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 同步同步信息
     * @param context
     * @param userName
     * @param listener
     */
    public static void searchUser(Context context, String userName, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME, userName)
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 添加联系人
     * @param context
     * @param userName
     * @param cusername
     * @param listener
     */
    public static void addContact(Context context, String userName, String cusername, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_CONTACT)
                .addParam(I.Contact.USER_NAME, userName)
                .addParam(I.Contact.CU_NAME, cusername)
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 删除联系人
     * @param context
     * @param userName
     * @param cusername
     * @param listener
     */
    public static void delContact(Context context, String userName, String cusername, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_CONTACT)
                .addParam(I.Contact.USER_NAME, userName)
                .addParam(I.Contact.CU_NAME, cusername)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void downloadContact(Context context, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DOWNLOAD_CONTACT_ALL_LIST)
                .addParam(I.Contact.USER_NAME, EMClient.getInstance().getCurrentUser())
                .targetClass(String.class)
                .execute(listener);

    }

    public static void createGroup(Context context, EMGroup emGroup, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_GROUP)
                .addParam(I.Group.HX_ID, emGroup.getGroupId())
                .addParam(I.Group.NAME, emGroup.getGroupName())
                .addParam(I.Group.DESCRIPTION, emGroup.getDescription())
                .addParam(I.Group.OWNER, emGroup.getOwner())
                .addParam(I.Group.IS_PUBLIC, emGroup.isPublic() + "")
                .addParam(I.Group.ALLOW_INVITES, emGroup.isAllowInvites() + "")
                .targetClass(String.class)
                .post()
                .execute(listener);
    }
    public static void createGroup(Context context, EMGroup emGroup,File file, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_GROUP)
                .addParam(I.Group.HX_ID, emGroup.getGroupId())
                .addParam(I.Group.NAME, emGroup.getGroupName())
                .addParam(I.Group.DESCRIPTION, emGroup.getDescription())
                .addParam(I.Group.OWNER, emGroup.getOwner())
                .addParam(I.Group.IS_PUBLIC, emGroup.isPublic() + "")
                .addParam(I.Group.ALLOW_INVITES, emGroup.isAllowInvites() + "")
                .targetClass(String.class)
                .addFile2(file)
                .post()
                .execute(listener);
    }

    public static void addGroupMembers(Context context, EMGroup emGroup, OkHttpUtils.OnCompleteListener<String> listener) {
        String memberArr = "";
        for (String m : emGroup.getMembers()) {
            if (!m.equals(SuperWeChatHelper.getInstance().getCurrentUsernName())) {
                memberArr += m + ",";
            }
        }
        memberArr = memberArr.substring(0, memberArr.length() - 1);
        L.e("addGroupMembers", "memberArr=" + memberArr);
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_GROUP_MEMBERS)
                .addParam(I.Member.GROUP_HX_ID, emGroup.getGroupId())
                .addParam(I.Member.USER_NAME, memberArr)
                .targetClass(String.class)
                .execute(listener);
    }

}
