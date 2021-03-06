package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.iView.IRegisterView;
import com.example.dc.refrigeratorproject.presenter.RegisterPresenter;
import com.example.dc.refrigeratorproject.resposeBean.User;
import com.example.dc.refrigeratorproject.util.CountDownButtonHelper;
import com.example.dc.refrigeratorproject.util.InputUtils;
import com.example.dc.refrigeratorproject.util.ToastUtil;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, IRegisterView {
    private TextView tvBack;
    private Button btnGetCode;
    private Button btnRegister;
    private EditText etPhone;
    private EditText etCode;
    private EditText etPsd;
    private RelativeLayout rlPsd;
    private RegisterPresenter presenter;
    private String openId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);
        presenter = new RegisterPresenter (RegisterActivity.this, this);
        openId = getIntent ().getStringExtra ("openId");

        rlPsd = findViewById (R.id.rl_psd);
        tvBack = (TextView) findViewById (R.id.tv_back_to_login);
        btnGetCode = (Button) findViewById (R.id.btn_get_code);
        btnRegister = (Button) findViewById (R.id.btn_register);
        etPhone = (EditText) findViewById (R.id.et_phone_number);
        etCode = (EditText) findViewById (R.id.et_code);
        etPsd = (EditText) findViewById (R.id.et_psd);
        tvBack.setOnClickListener (this);
        btnGetCode.setOnClickListener (this);
        btnRegister.setOnClickListener (this);

        if (openId != null) {
            rlPsd.setVisibility (View.GONE);
            btnRegister.setText ("绑定");
        } else {
            rlPsd.setVisibility (View.VISIBLE);
            btnRegister.setText ("注册");
        }

    }

    @Override
    public void onRegisterSuccess(User user) {
        Config.setUser (RegisterActivity.this, user);
        Config.setUserAccount (RegisterActivity.this, user.getAccount ());
        Config.setUserPsd (RegisterActivity.this, user.getPassword ());
        Config.setUserID (RegisterActivity.this, user.getUserId ());
        Config.setCreateFridgeIds (RegisterActivity.this, 0);
        Config.setSharedFridgeIds (RegisterActivity.this, user.getSharedFridgeIds ());
        Intent intent = new Intent (RegisterActivity.this, MainActivity.class);
        startActivity (intent);
        finish ();
    }

    @Override
    public void onBindSuccess(User user){
        Config.setUser (RegisterActivity.this, user);
        Config.setUserAccount (RegisterActivity.this, user.getAccount ());
        Config.setUserPsd (RegisterActivity.this, user.getPassword ());
        Config.setUserID (RegisterActivity.this, user.getUserId ());
        Config.setCreateFridgeIds (RegisterActivity.this, 0);
        Config.setSharedFridgeIds (RegisterActivity.this, user.getSharedFridgeIds ());
        Intent intent = new Intent (RegisterActivity.this, MainActivity.class);
        startActivity (intent);
        finish ();
    }

    @Override
    public void onBindFailure(String s){
        ToastUtil.showShort (RegisterActivity.this,s);
    }

    @Override
    public void onError(String result) {
        ToastUtil.showShort (RegisterActivity.this, result);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId ()) {
            case R.id.tv_back_to_login:
                finish ();
                break;
            case R.id.btn_get_code:
                //todo:发送验证码
                CountDownButtonHelper helper = new CountDownButtonHelper (btnGetCode,
                        getString (R.string.label_get_code), this, 30, 1);
                helper.setOnFinishListener (new CountDownButtonHelper.OnFinishListener () {

                    @Override
                    public void finish() {

                    }
                });
                helper.start ();
                break;
            case R.id.btn_register:
                if (TextUtils.isEmpty (etPhone.getText ())) {
                    ToastUtil.showShort (getApplicationContext (), R.string.hint_register_phone);
                } else if (!InputUtils.isPhoneNumber (etPhone.getText ().toString ())) {
                    ToastUtil.showShort (getApplicationContext (), R.string.toast_phone_wrong);
                } else if (TextUtils.isEmpty (etCode.getText ())) {
                    ToastUtil.showShort (getApplicationContext (), R.string.hint_register_code);
                } else {
                    if(openId!=null){
                        presenter.bindUserByQQ (openId,etPhone.getText ().toString ());
                    }else {
                        if (TextUtils.isEmpty (etPsd.getText ())) {
                            ToastUtil.showShort (getApplicationContext (), R.string.toast_no_psd);
                        } else {
                            presenter.register (etPhone.getText ().toString ().trim (), etPsd.getText ().toString ().trim ());
                        }
                    }
                }

                break;
        }
    }
}
