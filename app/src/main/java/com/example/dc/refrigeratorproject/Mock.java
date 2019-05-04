package com.example.dc.refrigeratorproject;

import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorModel;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorSharerModel;
import com.example.dc.refrigeratorproject.resposeBean.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DC on 2019/5/2.
 */

public class Mock {
    public Mock() {

    }

    public static List<RefrigeratorModel> getRefrigeratorList() {
        List<RefrigeratorModel> models = new ArrayList<> ();
        RefrigeratorModel model = getRefrigeratorModel1 ();

        RefrigeratorModel model2 = getRefrigeratorModel2 ();
        models.add (model2);
        models.add (model2);
        models.add (model2);
        models.add (model);
        return models;

    }

    public static RefrigeratorModel getRefrigeratorModel1() {
        RefrigeratorModel model = new RefrigeratorModel ();
        UserModel creator = new UserModel ();
        creator.setName ("司二北");
        creator.setAccount (1234);
        creator.setHead ("");
        model.setAddress ("浙江省杭州市西区胡");
        model.setName ("司而北的冰箱");
        model.setCreator (creator);
        model.setCurrentRefrigerator (true);

        List<RefrigeratorSharerModel> list = new ArrayList<> ();

        RefrigeratorSharerModel model1 = new RefrigeratorSharerModel ();
        model1.setCreator (false);
        UserModel sharer1 = new UserModel ();
        sharer1.setName ("测试1");
        sharer1.setAccount (12345677);
        sharer1.setHead ("");
        model1.setSharer (sharer1);
        list.add (model1);

        RefrigeratorSharerModel model2 = new RefrigeratorSharerModel ();
        model2.setCreator (false);
        UserModel sharer3 = new UserModel ();
        sharer3.setName ("哈哈哈");
        sharer3.setAccount (123456);
        sharer3.setHead ("");
        model2.setSharer (sharer3);
        list.add (model2);

        model.setSharerModelList (list);
        return model;
    }

    public static RefrigeratorModel getRefrigeratorModel2() {
        RefrigeratorModel model = new RefrigeratorModel ();
        UserModel creator = new UserModel ();
        creator.setName ("哈哈哈");
        creator.setAccount (123456);
        creator.setHead ("");
        model.setAddress ("浙江省杭州市滨江区");
        model.setName ("哈哈哈123");
        model.setCreator (creator);
        model.setCurrentRefrigerator (false);

        List<RefrigeratorSharerModel> list = new ArrayList<> ();

        RefrigeratorSharerModel model1 = new RefrigeratorSharerModel ();
        model1.setCreator (false);
        UserModel sharer1 = new UserModel ();
        sharer1.setName ("测试1");
        sharer1.setAccount (12345677);
        sharer1.setHead ("");
        model1.setSharer (sharer1);
        list.add (model1);

        RefrigeratorSharerModel model2 = new RefrigeratorSharerModel ();
        model2.setCreator (true);
        UserModel sharer3 = new UserModel ();
        sharer3.setName ("哈哈哈");
        sharer3.setAccount (123456);
        sharer3.setHead ("");
        model2.setSharer (sharer3);
        list.add (model2);

        model.setSharerModelList (list);
        return model;
    }
}
