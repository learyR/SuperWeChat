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

public class LoginActivity_ViewBinding<T extends LoginActivity> implements Unbinder {
  protected T target;

  private View view2131624054;

  private View view2131624196;

  private View view2131624197;

  @UiThread
  public LoginActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.img_back, "field 'imgBack' and method 'onClick'");
    target.imgBack = Utils.castView(view, R.id.img_back, "field 'imgBack'", ImageView.class);
    view2131624054 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.txtTitle = Utils.findRequiredViewAsType(source, R.id.txt_title, "field 'txtTitle'", TextView.class);
    target.userName = Utils.findRequiredViewAsType(source, R.id.username, "field 'userName'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnRegister, "method 'onClick'");
    view2131624196 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_Login, "method 'onClick'");
    view2131624197 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imgBack = null;
    target.txtTitle = null;
    target.userName = null;
    target.password = null;

    view2131624054.setOnClickListener(null);
    view2131624054 = null;
    view2131624196.setOnClickListener(null);
    view2131624196 = null;
    view2131624197.setOnClickListener(null);
    view2131624197 = null;

    this.target = null;
  }
}
