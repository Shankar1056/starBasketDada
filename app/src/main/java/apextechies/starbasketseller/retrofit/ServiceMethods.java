package apextechies.starbasketseller.retrofit;


import apextechies.starbasketseller.model.CategoryModel;
import apextechies.starbasketseller.model.SubCategoryModel;
import apextechies.starbasketseller.model.SubSubCategoryModel;

/**
 * Created by Shankar on 1/27/2018.
 */

public interface ServiceMethods {
    void category(DownlodableCallback<CategoryModel> callback);
    void subCategory(String cat_id, DownlodableCallback<SubCategoryModel> callback);
    void subSubCategory(String sub_cat_id, DownlodableCallback<SubSubCategoryModel> callback);
    /* void teacherList(DownlodableCallback<TeacherListModel> callback);
*/
}
