package apextechies.starbasketseller.retrofit;


import apextechies.starbasketseller.model.CategoryModel;
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



}
