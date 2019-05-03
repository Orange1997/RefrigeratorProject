package com.example.dc.refrigeratorproject;

import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorModel;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorSharerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DC on 2019/5/2.
 */

public class Mock {
    public Mock(){

    }

    public static List<RefrigeratorModel> getRefrigeratorList(){
        List<RefrigeratorModel> models = new ArrayList<> ();
        models.add (getRefrigeratorModel ());
        models.add (getRefrigeratorModel ());
        models.add (getRefrigeratorModel ());
        RefrigeratorModel model = getRefrigeratorModel ();
        model.setCurrentRefrigerator (true);
        models.add (model);
        models.add (getRefrigeratorModel ());
        return models;

    }
    public static RefrigeratorModel getRefrigeratorModel() {
        RefrigeratorModel model = new RefrigeratorModel ();
        model.setAddress ("浙江省杭州市西区胡留下街道斤斤计较斤斤计较急急急就将计就计");
        model.setName ("司而北的冰箱");
        model.setCreatorAccount (1234);
        model.setCreatorName ("司而北");
        model.setCurrentRefrigerator (false);

        List<RefrigeratorSharerModel> list = new ArrayList<> ();

        for (int i = 0; i < 8; i++) {
            RefrigeratorSharerModel model1 = new RefrigeratorSharerModel ();
            model1.setCreator (false);
            model1.setSharerName ("测试1");
            model1.setSharerAccount (12345677);
            list.add (model1);
        }

        RefrigeratorSharerModel model3 = new RefrigeratorSharerModel ();
        model3.setCreator (true);
        model3.setSharerName ("司而北");
        model3.setSharerAccount (1234);
        list.add (model3);

        RefrigeratorSharerModel model2 = new RefrigeratorSharerModel ();
        model2.setCreator (false);
        model2.setSharerName ("哈哈哈");
        model2.setSharerAccount (123456);
        list.add (model2);

        model.setSharerModelList (list);
        return model;
    }
}
