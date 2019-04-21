package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.util.CountDownButtonHelper;
import com.example.dc.refrigeratorproject.util.InputUtils;
import com.example.dc.refrigeratorproject.util.ToastUtil;

public class ForgetPsdActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvBack;
    private Button btnGetCode;
    private Button btnRegister;
    private EditText etPhone;
    private EditText etCode;
    private EditText etPsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_forget_psd);

        tvBack = (TextView) findViewById (R.id.tv_back_to_login);
        btnGetCode = (Button) findViewById (R.id.btn_get_code);
        btnRegister = (Button) findViewById (R.id.btn_register);
        etPhone = (EditText) findViewById (R.id.et_phone_number);
        etCode = (EditText) findViewById (R.id.et_code);
        etPsd = (EditText) findViewById (R.id.et_psd);
        tvBack.setOnClickListener (this);
        btnGetCode.setOnClickListener (this);
        btnRegister.setOnClickListener (this);
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
                        getString (R.string.label_get_code), this,30, 1);
                helper.setOnFinishListener (new CountDownButtonHelper.OnFinishListener () {

                    @Override
                    public void finish() {

                    }
                });
                helper.start ();
                break;
            case R.id.btn_register:
                //todo:注册
//                intent = new Intent (RegisterActivity.this,MainActivity.class);
//                startActivity (intent);
                if (TextUtils.isEmpty (etPhone.getText ())) {
                    ToastUtil.showShort (getApplicationContext (), R.string.hint_register_phone);
                } else if (!InputUtils.isPhoneNumber (etPhone.getText ().toString ())) {
                    ToastUtil.showShort (getApplicationContext (), R.string.toast_phone_wrong);
                } else if (TextUtils.isEmpty (etCode.getText ())) {
                    ToastUtil.showShort (getApplicationContext (), R.string.hint_register_code);
                } else if (TextUtils.isEmpty (etPsd.getText ())) {
                    ToastUtil.showShort (getApplicationContext (), R.string.toast_no_psd);
                }
                break;
        }
    }
}
