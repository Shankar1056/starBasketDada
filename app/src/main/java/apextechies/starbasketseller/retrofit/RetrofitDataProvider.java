package apextechies.starbasketseller.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import apextechies.starbasketseller.R;
import apextechies.starbasketseller.model.CategoryModel;
import apextechies.starbasketseller.model.SubCategoryModel;
import apextechies.starbasketseller.model.SubSubCategoryModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shankar on 1/27/2018.
 */

public class RetrofitDataProvider extends AppCompatActivity implements ServiceMethods {
    Context context;

    private ApiRetrofitService createRetrofitService() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiRetrofitService.class);
    }

    public RetrofitDataProvider(Context context) {
        this.context = context;
    }

    @Override
    public void category(final DownlodableCallback<CategoryModel> callback) {
        createRetrofitService().categoryList().enqueue(
                new Callback<CategoryModel>() {
                    @Override
                    public void onResponse(@NonNull Call<CategoryModel> call, @NonNull final Response<CategoryModel> response) {
                        if (response.isSuccessful()) {
                            CategoryModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CategoryModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void subCategory(String cat_id, final DownlodableCallback<SubCategoryModel> callback) {
        createRetrofitService().subcategoryList(cat_id).enqueue(
                new Callback<SubCategoryModel>() {
                    @Override
                    public void onResponse(@NonNull Call<SubCategoryModel> call, @NonNull final Response<SubCategoryModel> response) {
                        if (response.isSuccessful()) {
                            SubCategoryModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SubCategoryModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void subSubCategory(String sub_cat_id, final DownlodableCallback<SubSubCategoryModel> callback) {
        createRetrofitService().subsubcategoryList(sub_cat_id).enqueue(
                new Callback<SubSubCategoryModel>() {
                    @Override
                    public void onResponse(@NonNull Call<SubSubCategoryModel> call, @NonNull final Response<SubSubCategoryModel> response) {
                        if (response.isSuccessful()) {
                            SubSubCategoryModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SubSubCategoryModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }


   /* @Override
    public void notification(String school_id, final DownlodableCallback<NotificationModel> callback) {
        createRetrofitService().otpLogin(school_id).enqueue(
                new Callback<NotificationModel>() {
                    @Override
                    public void onResponse(@NonNull Call<NotificationModel> call, @NonNull final Response<NotificationModel> response) {
                        if (response.isSuccessful()) {
                            NotificationModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                                Toast.makeText(context, context.getResources().getString(R.string.something_went_wrong_error_message), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<NotificationModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }
*/


}