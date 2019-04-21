package com.example.dc.refrigeratorproject.model;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.activity.ForgetPsdActivity;
import com.example.dc.refrigeratorproject.activity.RegisterActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DC on 2019/3/15.
 */

public class MenuModel {

    public MenuModel() {

    }

    private List<Menu> list;

    public List<Menu> getList() {
        //todo:正确跳转链接

        list = new ArrayList<> ();
        Menu myHome = new Menu ();
        myHome.setIcon (R.drawable.ic_ice);
        myHome.setTitle ("我的家");
        myHome.setCls (ForgetPsdActivity.class);
        list.add (myHome);

        list = new ArrayList<> ();
        Menu myAttention = new Menu ();
        myAttention.setIcon (R.drawable.ic_ice);
        myAttention.setTitle ("我的关注");
        myAttention.setCls (ForgetPsdActivity.class);
        list.add (myAttention);

        Menu myCollection = new Menu ();
        myCollection.setIcon (R.drawable.ic_ice);
        myCollection.setTitle ("我的收藏");
        myCollection.setCls (RegisterActivity.class);
        list.add (myCollection);

        Menu message = new Menu ();
        message.setIcon (R.drawable.ic_ice);
        message.setTitle ("消息中心");
        message.setCls (ForgetPsdActivity.class);
        list.add (message);

        Menu setting = new Menu ();
        setting.setIcon (R.drawable.ic_ice);
        setting.setTitle ("设置");
        setting.setCls (ForgetPsdActivity.class);
        list.add (setting);
        return list;
    }

    public class Menu {
        int icon;
        String title;
        Class<?> cls;

        public Class<?> getCls() {
            return cls;
        }

        public void setCls(Class<?> cls) {
            this.cls = cls;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


    }


}
