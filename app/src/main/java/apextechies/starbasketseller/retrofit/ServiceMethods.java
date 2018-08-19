package apextechies.starbasketseller.retrofit;


import org.jetbrains.annotations.Nullable;

import apextechies.starbasketseller.model.CategoryModel;
import apextechies.starbasketseller.model.InsertProductModel;
import apextechies.starbasketseller.model.LoginModel;
import apextechies.starbasketseller.model.OrderHistoryModel;
import apextechies.starbasketseller.model.ProductListModel;
import apextechies.starbasketseller.model.SubCategoryModel;
import apextechies.starbasketseller.model.SubSubCategoryModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    void login(String phone, DownlodableCallback<LoginModel> callback);
    void signup(String name, String email, String phone, String business_name, String address,String pincode,String password,String created_date, DownlodableCallback<LoginModel> callback);
    void login(String email,String password,String created_date, DownlodableCallback<LoginModel> callback);
    void productList(String seller_id, DownlodableCallback<ProductListModel> callback);
    void insertUpdate(String id, String prod_id, String unit, String actual_price, String selling_price,
                      String discount, String short_description, String full_description, String created_date, String insertupdate,
                      DownlodableCallback<InsertProductModel> callback);

    void deleteProduct(String id, String prod_id,String delete, DownlodableCallback<InsertProductModel> callback);
    void uploadimage(@Nullable MultipartBody.Part body, @Nullable RequestBody name, DownlodableCallback<Void> callback);
    void getSellerOrerList(String seller_id, DownlodableCallback<OrderHistoryModel> callback);



}
