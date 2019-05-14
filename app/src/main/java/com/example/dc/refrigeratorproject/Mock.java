package com.example.dc.refrigeratorproject;

import com.example.dc.refrigeratorproject.model.ArticleOrRecipesModel;
import com.example.dc.refrigeratorproject.model.FollowModel;
import com.example.dc.refrigeratorproject.model.ListModel;
import com.example.dc.refrigeratorproject.model.ShopModel;
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

    //冰箱列表
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

    //清单列表
    public static List<ListModel> getListModelList(){
        List<ListModel> listModelList = new ArrayList<> ();
        for (int i = 0;i<10;i++){
            ListModel listModel = new ListModel ();
            listModel.setTime (20150507L);
            listModel.setTitle ("周日采购清单");
            listModelList.add (listModel);
        }
        return listModelList;
    }

    //文章列表
    public static List<ArticleOrRecipesModel> getArticleModels(){
        List<ArticleOrRecipesModel> list = new ArrayList<> ();
        for (int i=0;i<10;i++){
            ArticleOrRecipesModel articleOrRecipesModel = new ArticleOrRecipesModel ();
            articleOrRecipesModel.setTitle ("今天吃什么？我来推荐一下吧我来推荐一下吧");
            articleOrRecipesModel.setDes ("今天吃什么？我来推荐一下吧我来推荐一下吧我来推荐一下吧我来推荐一下吧我来推荐一下吧");
            articleOrRecipesModel.setImg ("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557658509392&di=034b39f0d51e993c1423fea8b30aceea&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201502%2F02%2F20150202095127_UknhG.jpeg");
            articleOrRecipesModel.setUrl ("https://www.cnblogs.com/jeffen/p/6958235.html");
            articleOrRecipesModel.setAuthor ("哈哈哈哈");
            articleOrRecipesModel.setTime ("2019-05-07");
            list.add (articleOrRecipesModel);
        }

        return list;
    }

    //食谱列表
    public static List<ArticleOrRecipesModel> getRecipesModels(){
        List<ArticleOrRecipesModel> recipesModels = new ArrayList<> ();
        for (int i=0;i<5;i++){
            ArticleOrRecipesModel recipesModel = new ArticleOrRecipesModel ();
            recipesModel.setImg ("https://dpic.tiankong.com/38/fv/QJ6942396456.jpg?x-oss-process=style/670ws");
            recipesModel.setTitle ("麻婆豆腐");
            recipesModel.setLikes (120);
            recipesModel.setUrl ("https://baijiahao.baidu.com/s?id=1617085088335127139&wfr=spider&for=pc");
            recipesModels.add (recipesModel);
        }
        return recipesModels;
    }

    public static List<ShopModel> getShopModelList(){
        List<ShopModel> shopModels = new ArrayList<> ();
        for (int i= 0;i<10;i++){
            ShopModel shopModel = new ShopModel ();
            shopModel.setImg ("https://dpic.tiankong.com/38/fv/QJ6942396456.jpg?x-oss-process=style/670ws");
            shopModel.setName ("联华超市（留下店）");
            shopModel.setLikes (200);
            shopModel.setDiscount (5.0f);
            shopModel.setLocation ("西湖区留下街道浙江科技学院111");
            shopModels.add (shopModel);
        }
        return shopModels;
    }

    //关注列表
    public static List<FollowModel> getFollowModels(){
        List<FollowModel> list = new ArrayList<> ();
        for (int i=0;i<10;i++){
            FollowModel followModel = new FollowModel ();
            followModel.setImg ("https://dpic.tiankong.com/38/fv/QJ6942396456.jpg?x-oss-process=style/670ws");
            followModel.setName ("玩转冰箱");
            followModel.setIntro ("教你如何轻松打理冰箱");
            followModel.setFollow (true);
            list.add (followModel);
        }

        return list;
    }
}
