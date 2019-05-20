package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.iView.ILoginView;
import com.example.dc.refrigeratorproject.presenter.LoginPresenter;
import com.example.dc.refrigeratorproject.resposeBean.User;
import com.example.dc.refrigeratorproject.util.InputUtils;
import com.example.dc.refrigeratorproject.util.ToastUtil;

/**
 * Created by DC on 2019/3/5.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener,ILoginView{

    private EditText etUser;
    private EditText etPsd;
    private CheckBox cbPsd;
    private Button btnLogin;
    private TextView tvForgotPsd;
    private TextView tvRegister;
//    private ImageView ivQqLogin;

    private LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        presenter = new LoginPresenter (LoginActivity.this,this);

        etUser = (EditText) findViewById (R.id.et_user);
        etPsd = (EditText) findViewById (R.id.et_psd);
        cbPsd = (CheckBox) findViewById (R.id.cd_psd);
        btnLogin = (Button) findViewById (R.id.btn_login);
        tvForgotPsd = (TextView) findViewById (R.id.tv_forgot_psd);
        tvRegister = (TextView) findViewById (R.id.tv_to_register);
//        ivQqLogin = (ImageView) findViewById (R.id.iv_qq);
        btnLogin.setOnClickListener (this);
        tvForgotPsd.setOnClickListener (this);
        tvRegister.setOnClickListener (this);
//        ivQqLogin.setOnClickListener (this);
        cbPsd.setVisibility (View.GONE);

        etPsd.addTextChangedListener (new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty (etPsd.getText ())) {
                    cbPsd.setVisibility (View.GONE);
                } else {
                    cbPsd.setVisibility (View.VISIBLE);
                }
            }
        });

        cbPsd.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPsd.setInputType (InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    Editable editable = etPsd.getText ();
                    Selection.setSelection (editable, editable.length ());
                } else {
                    etPsd.setInputType (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Editable editable = etPsd.getText ();
                    Selection.setSelection (editable, editable.length ());
                }
            }
        });

    }

    @Override
    public void onSuccess(User user) {
        Config.setUser (LoginActivity.this,user);
        Config.setUserAccount (LoginActivity.this, user.getAccount ());
        Config.setUserPsd (LoginActivity.this, user.getPassword ());
        Config.setUserID (LoginActivity.this, user.getUserId ());
        Config.setCreateFridgeIds (LoginActivity.this, 0);
        Config.setSharedFridgeIds (LoginActivity.this,user.getSharedFridgeIds ());
        Intent intent = new Intent (LoginActivity.this,MainActivity.class);
        startActivity (intent);
        finish ();
    }

    @Override
    public void onError(String result) {
        ToastUtil.showShort (LoginActivity.this,result);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId ()) {
            case R.id.btn_login:
                if (TextUtils.isEmpty (etUser.getText ())) {
                    ToastUtil.showShort (getApplicationContext (), R.string.hint_register_phone);
                } else if (!InputUtils.isPhoneNumber (etUser.getText ().toString ())) {
                    ToastUtil.showShort (getApplicationContext (), R.string.toast_phone_wrong);
                } else if (TextUtils.isEmpty (etPsd.getText ())) {
                    ToastUtil.showShort (getApplicationContext (), R.string.toast_no_psd);
                } else {
                    presenter.login (etUser.getText ().toString (), etPsd.getText ().toString ());
                }
                break;
            case R.id.tv_forgot_psd:
                //todo:忘记密码跳转
                intent = new Intent (LoginActivity.this, ForgetPsdActivity.class);
                startActivity (intent);
                break;
            case R.id.tv_to_register:
                //todo:手机注册跳转
                intent = new Intent (LoginActivity.this, RegisterActivity.class);
                startActivity (intent);
                break;
//            case R.id.iv_qq:
//                //todo:QQ第三方登录
//                Toast.makeText (getApplicationContext (), "QQ登录跳转", Toast.LENGTH_SHORT).show ();
//                break;
        }
    }

    @Override
    public void onResume(){
        super.onResume ();
        if (Config.isLogin (LoginActivity.this)){
            Intent intent = new Intent (LoginActivity.this,MainActivity.class);
            startActivity (intent);
            finish ();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
    }

}
