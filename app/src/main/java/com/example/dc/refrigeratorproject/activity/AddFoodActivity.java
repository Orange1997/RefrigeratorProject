package com.example.dc.refrigeratorproject.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dc.refrigeratorproject.R;

import java.util.Calendar;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;

/**
 * project: RefrigeratorProject
 * author : Android
 * date : 2019/4/24
 * time : 10:04
 * email : 企业邮箱
 * note : 添加食物界面
 */
public class AddFoodActivity extends BaseActivity implements View.OnClickListener {
    private static final int CAMERA_CODE = 8888;
    private static final int GALLERY_CODE = 8000;
    private static final int CROP_CODE = 8001;
    private Toolbar mToolbar;
    private EditText etExpiredDate, etRemindTime,etType;
    private ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_food);
        initView ();
    }

    private void initView() {
        initTitle ();
        etExpiredDate = findViewById (R.id.et_expired_date);
        etRemindTime = findViewById (R.id.et_remind_time);
        etType = findViewById (R.id.et_food_type);
        ivPhoto = findViewById (R.id.iv_photo);
        etExpiredDate.setOnClickListener (this);
        etRemindTime.setOnClickListener (this);
        etType.setOnClickListener (this);
        ivPhoto.setOnClickListener (this);

    }

    private void initTitle() {
        mToolbar = findViewById (R.id.titlebar);
        setSupportActionBar (mToolbar);
        mToolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });

        mToolbar.setOnMenuItemClickListener (new Toolbar.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId ()) {
                    case R.id.action_done:
                        //todo:添加完成逻辑
                        Toast.makeText (AddFoodActivity.this, "done !", Toast.LENGTH_SHORT).show ();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_add_food, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()) {
            case R.id.et_expired_date:
                showDatePickerDialog (AddFoodActivity.this, etExpiredDate, null,true);
                break;
            case R.id.et_remind_time:
                showDatePickerDialog (AddFoodActivity.this, etExpiredDate,etRemindTime, false);
                break;
            case R.id.et_food_type:
                showDatePickerDialog (AddFoodActivity.this, etExpiredDate,etRemindTime, false);
                break;
            case R.id.iv_photo:
                showListDialog ();
                break;
        }
    }

    private void showListDialog() {
        final String[] items = {"拍照", "本地相册"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder (AddFoodActivity.this);
        listDialog.setItems (items, new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent cameraIntent = new Intent (android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult (cameraIntent, CAMERA_CODE);
                        break;
                    case 1:
                        Intent intent = new Intent (Intent.ACTION_GET_CONTENT);
                        intent.setType ("image/*");
                        startActivityForResult (intent, GALLERY_CODE);
                        break;
                }
            }
        });
        listDialog.show ();
    }

    private void showTypeDialog() {
        final String[] items = {"蔬菜", "肉类","海鲜"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder (AddFoodActivity.this);
        listDialog.setItems (items, new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent cameraIntent = new Intent (android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult (cameraIntent, CAMERA_CODE);
                        break;
                    case 1:
                        Intent intent = new Intent (Intent.ACTION_GET_CONTENT);
                        intent.setType ("image/*");
                        startActivityForResult (intent, GALLERY_CODE);
                        break;
                }
            }
        });
        listDialog.show ();
    }

    //日期选择
    public static void showDatePickerDialog(final Activity activity, final EditText tv, final EditText tv2, final boolean onlyDate) {
        Calendar calendar = Calendar.getInstance ();
        new DatePickerDialog (activity, THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener () {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (onlyDate) {
                    tv.setText (year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                } else {
                    String date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                    showTimePickerDialog (activity, tv2, date);
                }
            }
        }, calendar.get (Calendar.YEAR), calendar.get (Calendar.MONTH), calendar.get (Calendar.DAY_OF_MONTH)).show ();
    }

    //时间选择
    public static void showTimePickerDialog(Activity activity, final EditText tv, final String date) {
        Calendar calendar = Calendar.getInstance ();
        new TimePickerDialog (activity, THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener () {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (hourOfDay < 10 && minute >= 10) {
                    tv.setText (date + " " + "0" + hourOfDay + ":" + minute);
                }
                if (minute < 10 && hourOfDay >= 10) {
                    tv.setText (date + " " + hourOfDay + ":" + "0" + minute);
                }
                if (hourOfDay < 10 && minute < 10) {
                    tv.setText (date + " " + "0" + hourOfDay + ":" + "0" + minute);
                }
                if (hourOfDay >= 10 && minute >= 10) {
                    tv.setText (date + " " + hourOfDay + ":" + minute);
                }
            }
        }, calendar.get (Calendar.HOUR_OF_DAY), calendar.get (Calendar.MINUTE), true).show ();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_CODE:
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText (this, "取消了拍照", Toast.LENGTH_LONG).show ();
                    return;
                }
                if (data != null) {
                    Bitmap photo = data.getParcelableExtra ("data");
                    ivPhoto.setImageBitmap (photo);
                }
                break;
            case GALLERY_CODE:
                if (data == null) {
                    return;
                } else {
//                    //用户从图库选择图片后会返回所选图片的Uri
//                    Uri uri;
//                    //获取到用户所选图片的Uri
//                    uri = data.getData();
//                    //返回的Uri为content类型的Uri,不能进行复制等操作,需要转换为文件Uri
//                    uri = convertUri(uri);
//                    startImageZoom(uri);
                }
                break;
            case CROP_CODE:
                if (data == null) {
                    return;
                } else {
                    Bundle extras = data.getExtras ();
                    if (extras != null) {
                        //获取到裁剪后的图像
                        Bitmap bm = extras.getParcelable ("data");
                        ivPhoto.setImageBitmap (bm);
                    }
                }
                break;
            default:
                break;
        }
    }

}
