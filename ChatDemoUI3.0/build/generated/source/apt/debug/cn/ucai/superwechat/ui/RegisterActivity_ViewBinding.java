// Generated code from Butter Knife. Do not modify!
package cn.ucai.superwechat.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import cn.ucai.superwechat.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding<T extends RegisterActivity> implements Unbinder {
  protected T target;

  private View view2131624054;

  private View view2131624046;

  @UiThread
  public RegisterActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.username = Utils.findRequiredViewAsType(source, R.id.username, "field 'username'", EditText.class);
    target.nickName = Utils.findRequiredViewAsType(source, R.id.nickName, "field 'nickName'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    target.confirmPassword = Utils.findRequiredViewAsType(source, R.id.confirm_password, "field 'confirmPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.img_back, "field 'imgBack' and method 'onClickBack'");
    target.imgBack = Utils.castView(view, R.id.img_back, "field 'imgBack'", ImageView.class);
    view2131624054 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBack();
      }
    });
    target.txtTitle = Utils.findRequiredViewAsType(source, R.id.txt_title, "field 'txtTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_register, "method 'onClickRegister'");
    view2131624046 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickRegister();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.username = null;
    target.nickName = null;
    target.password = null;
    target.confirmPassword = null;
    target.imgBack = null;
    target.txtTitle = null;

    view2131624054.setOnClickListener(null);
    view2131624054 = null;
    view2131624046.setOnClickListener(null);
    view2131624046 = null;

    this.target = null;
  }
}
