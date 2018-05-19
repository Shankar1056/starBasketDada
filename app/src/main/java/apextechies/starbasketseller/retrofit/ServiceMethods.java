package apextechies.starbasketseller.retrofit;


import apextechies.starbasketseller.model.CategoryModel;
import apextechies.starbasketseller.model.InsertProductModel;
import apextechies.starbasketseller.model.SubCategoryModel;
import apextechies.starbasketseller.model.SubSubCategoryModel;

/**
 * Created by Shankar on 1/27/2018.
 */

public interface ServiceMethods {
    void category(DownlodableCallback<CategoryModel> callback);
    void subCategory(String cat_id, DownlodableCallback<SubCategoryModel> callback);
    void subSubCategory(String sub_cat_id, DownlodableCallback<SubSubCategoryModel> callback);
    void insertProduct(String sub_cat_id, String sub_sub_cat_id, String name, String unit, String actual_price, String selling_price,
                       String discount, String short_description, String full_description, String seller_id, String created_date,
                       DownlodableCallback<InsertProductModel> callback);


    /* void teacherList(DownlodableCallback<TeacherListModel> callback);
*/
}
