package com.example.dc.refrigeratorproject.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.model.City;
import com.example.dc.refrigeratorproject.util.ToastUtil;
import com.google.gson.Gson;
import com.yiguo.adressselectorlib.AddressSelector;
import com.yiguo.adressselectorlib.CityInterface;
import com.yiguo.adressselectorlib.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static com.example.dc.refrigeratorproject.activity.RefrigeratorInfoActivity.RESULT_CODE_ADDRESS;
import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_ADDRESS;
import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_ADDRESS_AREA;
import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_ADDRESS_CITY;
import static com.example.dc.refrigeratorproject.config.Config.KEY_REFRIGERATOR_ADDRESS_PROVINCE;

/**
 * Created by DC on 2019/5/3.
 */

public class AddressSelectorActivity extends BaseActivity {

    private AddressSelector addressSelector;

    private ArrayList<City> cities1 = new ArrayList<> ();
    private ArrayList<City> cities2 = new ArrayList<> ();
    private ArrayList<City> cities3 = new ArrayList<> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_address_selector);

        //拿到本地JSON 并转成String
        try {
            JSONArray jsonArray = new JSONArray (getString (R.string.cities1));
            for (int i = 0; i < jsonArray.length (); i++) {
                cities1.add (new Gson ().fromJson (jsonArray.get (i).toString (), City.class));
            }
            JSONArray jsonArray2 = new JSONArray (getString (R.string.cities2));
            for (int i = 0; i < jsonArray2.length (); i++) {
                cities2.add (new Gson ().fromJson (jsonArray2.get (i).toString (), City.class));
            }
            JSONArray jsonArray3 = new JSONArray (getString (R.string.cities3));
            for (int i = 0; i < jsonArray3.length (); i++) {
                cities3.add (new Gson ().fromJson (jsonArray3.get (i).toString (), City.class));
            }
        } catch (JSONException e) {
            e.printStackTrace ();
        }

        initView ();
//        updateList (refrigeratorModel);
    }

    private void initView() {
        addressSelector = findViewById (R.id.address_selecor);
        addressSelector.setGrayLineColor (getResources ().getColor (R.color.transparent));
        addressSelector.setTabAmount (3);
        addressSelector.setCities (cities1);
        addressSelector.setOnItemClickListener (new OnItemClickListener () {
            @Override
            public void itemClick(AddressSelector addressSelector, CityInterface city, int tabPosition) {
                switch (tabPosition) {
                    case 0:
                        addressSelector.setCities (cities2);
                        Config.putString (AddressSelectorActivity.this, KEY_REFRIGERATOR_ADDRESS_PROVINCE, city.getCityName ());
                        break;
                    case 1:
                        addressSelector.setCities (cities3);
                        Config.putString (AddressSelectorActivity.this, KEY_REFRIGERATOR_ADDRESS_CITY, city.getCityName ());
                        break;
                    case 2:
                        Config.putString (AddressSelectorActivity.this, KEY_REFRIGERATOR_ADDRESS_AREA, city.getCityName ());
                        Intent intent = new Intent ();
                        intent.putExtra (KEY_REFRIGERATOR_ADDRESS,
                                Config.getString (AddressSelectorActivity.this, KEY_REFRIGERATOR_ADDRESS_PROVINCE)
                                        + Config.getString (AddressSelectorActivity.this, KEY_REFRIGERATOR_ADDRESS_CITY)
                                        + Config.getString (AddressSelectorActivity.this, KEY_REFRIGERATOR_ADDRESS_AREA));
                        setResult (RESULT_CODE_ADDRESS,intent);
                        ToastUtil.showShort (AddressSelectorActivity.this, "更新成功");
                        finish ();
                        break;
                }
            }
        });
        addressSelector.setOnTabSelectedListener (new AddressSelector.OnTabSelectedListener () {
            @Override
            public void onTabSelected(AddressSelector addressSelector, AddressSelector.Tab tab) {
                switch (tab.getIndex ()) {
                    case 0:
                        addressSelector.setCities (cities1);
                        break;
                    case 1:
                        addressSelector.setCities (cities2);
                        break;
                    case 2:
                        addressSelector.setCities (cities3);
                        break;
                }
            }

            @Override
            public void onTabReselected(AddressSelector addressSelector, AddressSelector.Tab tab) {

            }
        });

    }

}
