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
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.event.UpdateFridgeEvent;
import com.example.dc.refrigeratorproject.iView.IAddFoodView;
import com.example.dc.refrigeratorproject.presenter.AddFoodPresenter;
import com.example.dc.refrigeratorproject.resposeBean.FoodDetailRes;
import com.example.dc.refrigeratorproject.util.CalendarUtils;
import com.example.dc.refrigeratorproject.util.InputUtils;
import com.example.dc.refrigeratorproject.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

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
public class AddFoodActivity extends BaseActivity implements View.OnClickListener, IAddFoodView {
    private static final int CAMERA_CODE = 8888;
    private static final int GALLERY_CODE = 8000;
    private static final int CROP_CODE = 8001;
    private Toolbar mToolbar;
    private EditText etExpiredDate, etRemindTime, etFoodName, etCount, etUnit, etRemark, etType;
    private ImageView ivPhoto;
    private AddFoodPresenter presenter;
    private int type = -1;
    private FoodDetailRes foodDetailRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_food);
        foodDetailRes = (FoodDetailRes) getIntent ().getSerializableExtra ("foodDetail");
        presenter = new AddFoodPresenter (AddFoodActivity.this, this);
        initView ();
    }

    private void initView() {
        initTitle ();
        etExpiredDate = findViewById (R.id.et_expired_date);
        etRemindTime = findViewById (R.id.et_remind_time);
        etFoodName = findViewById (R.id.et_food_name);
        etCount = findViewById (R.id.et_food_amount);
        etUnit = findViewById (R.id.et_food_unit);
        etType = findViewById (R.id.et_food_type);
        etRemark = findViewById (R.id.et_remind_extra);
        ivPhoto = findViewById (R.id.iv_photo);
        etExpiredDate.setOnClickListener (this);
        etRemindTime.setOnClickListener (this);
        etType.setOnClickListener (this);
        ivPhoto.setOnClickListener (this);
        if (foodDetailRes != null) {
            etFoodName.setText (TextUtils.isEmpty (foodDetailRes.getFoodName ()) ? null : foodDetailRes.getFoodName ());
            if (foodDetailRes.getFoodType () != -1) {
                if (foodDetailRes.getFoodType () == 1) {
                    type = 1;
                    etType.setText ("蔬菜水果");
                } else if (foodDetailRes.getFoodType () == 2) {
                    type = 2;
                    etType.setText ("海鲜肉类");
                } else if (foodDetailRes.getFoodType () == 3) {
                    type = 3;
                    etType.setText ("熟食糕点");
                } else if (foodDetailRes.getFoodType () == 4) {
                    type = 4;
                    etType.setText ("其他");
                }
                if (foodDetailRes.getOutlineTime () > 0) {
                    etExpiredDate.setText (InputUtils.getDateToString (foodDetailRes.getOutlineTime (), "yyyy-MM-dd"));
                }
                if (foodDetailRes.getFoodCount () > 0) {
                    etCount.setText (String.valueOf (foodDetailRes.getFoodCount ()));
                }
                if (TextUtils.isEmpty (foodDetailRes.getFoodUnit ())) {
                    etUnit.setText (foodDetailRes.getFoodUnit ());
                }
                if (foodDetailRes.getRemindTime () > 0) {
                    etRemindTime.setText (InputUtils.getDateToString (foodDetailRes.getRemindTime (), "yyyy-MM-dd HH:mm"));
                }
                if (TextUtils.isEmpty (foodDetailRes.getRemark ())) {
                    etRemark.setText (foodDetailRes.getRemark ());
                }
            }
        }

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
        TextView title = findViewById (R.id.tv_title);
        if (foodDetailRes != null) {
            title.setText ("编辑食物");
        } else {
            title.setText ("添加食物");
        }

        mToolbar.setOnMenuItemClickListener (new Toolbar.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId ()) {
                    case R.id.action_done:
                        //todo:添加完成逻辑
                        if (foodDetailRes == null) {
                            if (TextUtils.isEmpty (etFoodName.getText ())) {
                                ToastUtil.showShort (AddFoodActivity.this, "食物名称不能为空");
                            } else if (TextUtils.isEmpty (etType.getText ())) {
                                ToastUtil.showShort (AddFoodActivity.this, "请设置食物类型");
                            } else if (TextUtils.isEmpty (etExpiredDate.getText ())) {
                                ToastUtil.showShort (AddFoodActivity.this, "请设置食物过期时间");
                            } else {
                                String foodName = etFoodName.getText ().toString ();
                                float amount = -1;
                                if (!TextUtils.isEmpty (etCount.getText ().toString ())) {
                                    amount = Float.parseFloat (etCount.getText ().toString ());
                                }
                                String unit = TextUtils.isEmpty (etUnit.getText ().toString ()) ? "" : etUnit.getText ().toString ();
                                long outTime = InputUtils.getStringToDate (etExpiredDate.getText ().toString (), "yyyy-MM-dd");
                                String remindTimeString = etRemindTime.getText ().toString ();
                                long remindTime = InputUtils.getStringToDate (remindTimeString, "yyyy-MM-dd HH:mm");
                                String remark = TextUtils.isEmpty (etRemark.getText ().toString ()) ? "" : etRemark.getText ().toString ();
                                presenter.addFood (foodName, amount, unit, outTime, remindTime, remark, type);
                            }
                        } else {
                            String foodName = etFoodName.getText ().toString ();
                            float amount = -1;
                            if (!TextUtils.isEmpty (etCount.getText ().toString ())) {
                                amount = Float.parseFloat (etCount.getText ().toString ());
                            }
                            String unit = TextUtils.isEmpty (etUnit.getText ().toString ()) ? "" : etUnit.getText ().toString ();
                            long outTime = InputUtils.getStringToDate (etExpiredDate.getText ().toString (), "yyyy-MM-dd");
                            String remindTimeString = etRemindTime.getText ().toString ();
                            long remindTime = InputUtils.getStringToDate (remindTimeString, "yyyy-MM-dd HH:mm");
                            String remark = TextUtils.isEmpty (etRemark.getText ().toString ()) ? "" : etRemark.getText ().toString ();
                            presenter.updateFoodPost (foodName, amount, unit, outTime, remindTime, remark, foodDetailRes.getFoodId (), type);
                        }

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
                showDatePickerDialog (AddFoodActivity.this, etExpiredDate, null, true);
                break;
            case R.id.et_remind_time:
                showDatePickerDialog (AddFoodActivity.this, etExpiredDate, etRemindTime, false);
                break;
            case R.id.et_food_type:
                showTypeDialog ();
                break;
            case R.id.iv_photo:
                showListDialog ();
                break;
        }
    }

    @Override
    public void onAddFoodSuccess(String s) {
        ToastUtil.showShort (AddFoodActivity.this, s);
        EventBus.getDefault ().post (new UpdateFridgeEvent (Config.getCurrentFridgeId (AddFoodActivity.this)));
        String remindTimeString = etRemindTime.getText ().toString ();
        String foodName = etFoodName.getText ().toString ();
        long remindTime = InputUtils.getStringToDate (remindTimeString, "yyyy-MM-dd HH:mm");
        if (remindTime > 0) {
            CalendarUtils.addCalendarEvent (this, foodName + "过期提醒", foodName + "就要过期啦，赶快去解决它们吧", remindTime, 0);
        } else {
            remindTimeString = etExpiredDate.getText ().toString () + " 08:00";
            remindTime = InputUtils.getStringToDate (remindTimeString, "yyyy-MM-dd HH:mm");
            CalendarUtils.addCalendarEvent (this, foodName + "过期提醒", foodName + "就要过期啦，赶快去解决它们吧", remindTime, 0);
        }
        finish ();
    }

    @Override
    public void onUpdateFoodSuccess(String s){
        finish ();
    }

    @Override
    public void onError(String s) {

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
        final String[] items = {"蔬菜水果", "海鲜肉类", "熟食糕点", "其他"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder (AddFoodActivity.this);
        listDialog.setItems (items, new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        etType.setText (items[which]);
                        type = 1;
                        break;
                    case 1:
                        etType.setText (items[which]);
                        type = 2;
                        break;
                    case 2:
                        etType.setText (items[which]);
                        type = 3;
                        break;
                    case 3:
                        etType.setText (items[which]);
                        type = 4;
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
                    if (monthOfYear > 9 && dayOfMonth > 9) {
                        tv.setText (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    } else if (monthOfYear <= 9 && dayOfMonth > 9) {
                        tv.setText (year + "-" + "0" + (monthOfYear + 1) + "-" + dayOfMonth);
                    } else if (monthOfYear <= 9 && dayOfMonth <= 9) {
                        tv.setText (year + "-" + "0" + (monthOfYear + 1) + "-" + "0" + dayOfMonth);
                    } else if (monthOfYear > 9 && dayOfMonth <= 9) {
                        tv.setText (year + "-" + (monthOfYear + 1) + "-" + "0" + dayOfMonth);
                    }
                } else {
                    String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
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
