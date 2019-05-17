package com.example.dc.refrigeratorproject.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.resposeBean.User;
import com.facebook.drawee.view.SimpleDraweeView;

import static com.example.dc.refrigeratorproject.activity.EditSignActivity.RESULT_CODE_EDIT_SIGN;
import static com.example.dc.refrigeratorproject.activity.RefrigeratorInfoActivity.REQUEST_CODE;
import static com.example.dc.refrigeratorproject.activity.RefrigeratorInfoActivity.RESULT_CODE_ADDRESS;
import static com.example.dc.refrigeratorproject.activity.RefrigeratorInfoActivity.RESULT_CODE_EDIT_NAME;
import static com.example.dc.refrigeratorproject.config.Config.KEY_EDIT_NAME;
import static com.example.dc.refrigeratorproject.config.Config.KEY_EDIT_SIGN_CONTENT;
import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_ADDRESS;


/**
 * Created by DC on 2019/4/21.
 */

public class PersonalInfoActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvName, tvSex, tvAddress, tvIntro,tvAccount;
    private SimpleDraweeView ivHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_person_info);

        initView ();
    }

    private void initView() {
        Toolbar toolbar = findViewById (R.id.titlebar);
        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });

        tvAccount = findViewById (R.id.et_account);
        tvName = findViewById (R.id.et_name);
        tvSex = findViewById (R.id.et_sex);
        tvAddress = findViewById (R.id.et_address);
        tvIntro = findViewById (R.id.et_intro);
        ivHead = findViewById (R.id.iv_head);

        tvName.setOnClickListener (this);
        tvSex.setOnClickListener (this);
        tvAddress.setOnClickListener (this);
        tvIntro.setOnClickListener (this);

        User user = Config.getUser (PersonalInfoActivity.this);
        if (user!=null){
            tvAccount.setText (user.getAccount ());
            tvName.setText (user.getUserName ()!=null?user.getUserName ():"用户"+user.getAccount ());
            tvSex.setText ("女");
            tvAddress.setText ("浙江杭州");
            tvIntro.setText ("一个小透明");
        }


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId ()) {
            case R.id.et_name:
                intent = new Intent (PersonalInfoActivity.this, EditNameActivity.class);
                intent.putExtra (KEY_EDIT_NAME, tvName.getText ().toString ());
                startActivityForResult (intent, REQUEST_CODE);
                break;
            case R.id.et_sex:
                showSingleChoiceDialog (!TextUtils.isEmpty (tvSex.getText ().toString ()) ? tvSex.getText ().toString () : "null");
                break;
            case R.id.et_address:
                intent = new Intent (PersonalInfoActivity.this, AddressSelectorActivity.class);
                startActivityForResult (intent, REQUEST_CODE);
                break;
            case R.id.et_intro:
                intent = new Intent (PersonalInfoActivity.this, EditSignActivity.class);
                startActivityForResult (intent, REQUEST_CODE);
                break;
        }
    }

    private void showSingleChoiceDialog(String content) {
        final String[] items = {"男", "女"};
        int position = 0;
        for (int i = 0; i < items.length; i++) {
            if (content.equals (items[i])) {
                position = i;
            }
        }
        final AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder (PersonalInfoActivity.this);
        singleChoiceDialog.setSingleChoiceItems (items, position,
                new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvSex.setText (items[which]);
                        dialog.dismiss ();
                    }
                });
        singleChoiceDialog.show ();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent != null) {
            if (resultCode == RESULT_CODE_EDIT_NAME) {
                String result = intent.getStringExtra (KEY_EDIT_NAME);
                tvName.setText (result);
            } else if (resultCode == RESULT_CODE_ADDRESS) {
                String result = intent.getStringExtra (KEY_REFRIGERATOR_ADDRESS);
                tvAddress.setText (result);
            } else if (resultCode == RESULT_CODE_EDIT_SIGN) {
                String result = intent.getStringExtra (KEY_EDIT_SIGN_CONTENT);
                tvIntro.setText (result);
            }
        }
    }
}
