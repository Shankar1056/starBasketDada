package apextechies.starbasketseller.retrofit;


import apextechies.starbasketseller.model.CategoryModel;
import apextechies.starbasketseller.model.InsertProductModel;
import apextechies.starbasketseller.model.LoginModel;
import apextechies.starbasketseller.model.ProductListModel;
import apextechies.starbasketseller.model.SubCategoryModel;
import apextechies.starbasketseller.model.SubSubCategoryModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Shankar on 1/27/2018.
 */

public interface ApiRetrofitService {
    /*@POST(ApiUrl.NOTIFICATION)
    @FormUrlEncoded
    Call<NotificationModel> otpLogin(@Field("school_id") String school_id);*/


    @GET(ApiUrl.CATEGORYLIST)
    Call<CategoryModel> categoryList();

    @POST(ApiUrl.SUBCATEGORYLIST)
    @FormUrlEncoded
    Call<SubCategoryModel> subcategoryList(@Field("cat_id") String subcatId);

    @POST(ApiUrl.SUBSUBCATEGORYLIST)
    @FormUrlEncoded
    Call<SubSubCategoryModel> subsubcategoryList(@Field("sub_cat_id") String subsubcatId);

    @POST(ApiUrl.INSERTPRODUCT)
    @FormUrlEncoded
    Call<InsertProductModel> insertProduct(@Field("sub_cat_id") String sub_cat_id, @Field("sub_sub_cat_id") String sub_sub_cat_id,
                                           @Field("name") String name, @Field("unit") String unit,
                                           @Field("actual_price") String actual_price, @Field("selling_price") String selling_price,
                                           @Field("discount") String discount, @Field("short_description") String short_description,
                                           @Field("full_description") String full_description, @Field("seller_id") String seller_id,
                                           @Field("created_date") String created_date);

    @POST(ApiUrl.LOGIN)
    @FormUrlEncoded
    Call<LoginModel> login(@Field("phone") String phone);

    @POST(ApiUrl.SIGNUP)
    @FormUrlEncoded
    Call<LoginModel> signup(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("phone") String phone, @Field("business_name") String business_name, @Field("address") String address, @Field("created_date") String created_date);

    @POST(ApiUrl.PRODUCTLIST)
    @FormUrlEncoded
    Call<ProductListModel> productList(@Field("seller_id") String seller_id);

    @POST(ApiUrl.INSERTUPDATEVARIENT)
    @FormUrlEncoded
    Call<InsertProductModel> insertUpdateVarient(@Field("id") String sub_cat_id, @Field("prod_id") String prod_id,
                                               @Field("unit") String unit, @Field("actual_price") String actual_price, @Field("selling_price") String selling_price,
                                               @Field("discount") String discount, @Field("short_description") String short_description,
                                               @Field("full_description") String full_description, @Field("created_date") String created_date,
                                               @Field("insertupdate") String insertupdate);
    @POST(ApiUrl.DELETEVARIENT)
    @FormUrlEncoded
    Call<InsertProductModel> deleteProduct(@Field("id") String seller_id, @Field("prod_id") String prod_id, @Field("all") String delete);

}
