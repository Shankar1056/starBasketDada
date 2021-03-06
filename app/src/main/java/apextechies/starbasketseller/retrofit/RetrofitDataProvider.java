package apextechies.starbasketseller.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

import apextechies.starbasketseller.R;
import apextechies.starbasketseller.model.CategoryModel;
import apextechies.starbasketseller.model.InsertProductModel;
import apextechies.starbasketseller.model.LoginModel;
import apextechies.starbasketseller.model.OrderDetailsModel;
import apextechies.starbasketseller.model.OrderHistoryModel;
import apextechies.starbasketseller.model.ProductListModel;
import apextechies.starbasketseller.model.SubCategoryModel;
import apextechies.starbasketseller.model.SubSubCategoryModel;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;

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

    @Override
    public void insertProduct(String sub_cat_id, String sub_sub_cat_id, String name, String unit, String productQuantity, String productBrand, String actual_price, String selling_price, String discount, String short_description, String full_description, String seller_id, String created_date, final DownlodableCallback<InsertProductModel> callback) {
        createRetrofitService().insertProduct(sub_cat_id, sub_sub_cat_id, name, unit, productQuantity, productBrand, actual_price, selling_price, discount, short_description, full_description, seller_id, created_date).enqueue(
                new Callback<InsertProductModel>() {
                    @Override
                    public void onResponse(@NonNull Call<InsertProductModel> call, @NonNull final Response<InsertProductModel> response) {
                        if (response.isSuccessful()) {
                            InsertProductModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<InsertProductModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void login(String phone, final DownlodableCallback<LoginModel> callback) {
        createRetrofitService().login(phone).enqueue(
                new Callback<LoginModel>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginModel> call, @NonNull final Response<LoginModel> response) {
                        if (response.isSuccessful()) {
                            LoginModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void signup(String name, String lastname, String email, String phone, String business_name, String address,String pincode,String passwor, String created_date, final DownlodableCallback<LoginModel> callback) {
        createRetrofitService().signup(name, lastname, email, phone, business_name, address, pincode, passwor, created_date).enqueue(
                new Callback<LoginModel>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginModel> call, @NonNull final Response<LoginModel> response) {
                        if (response.isSuccessful()) {
                            LoginModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void login(String email, String password, String created_date, final DownlodableCallback<LoginModel> callback) {
        createRetrofitService().login(email,password, created_date).enqueue(
                new Callback<LoginModel>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginModel> call, @NonNull final Response<LoginModel> response) {
                        if (response.isSuccessful()) {
                            LoginModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void productList(String seller_id, final DownlodableCallback<ProductListModel> callback) {
        createRetrofitService().productList(seller_id).enqueue(
                new Callback<ProductListModel>() {
                    @Override
                    public void onResponse(@NonNull Call<ProductListModel> call, @NonNull final Response<ProductListModel> response) {
                        if (response.isSuccessful()) {
                            ProductListModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ProductListModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void insertUpdate(String id, String prod_id, String unit, String productQuantity, String productBrand, String actual_price, String selling_price, String discount, String short_description, String full_description, String created_date, String insertupdate, final DownlodableCallback<InsertProductModel> callback) {
        createRetrofitService().insertUpdateVarient(id, prod_id, unit, productQuantity, productBrand, actual_price, selling_price, discount, short_description, full_description, created_date, insertupdate).enqueue(
                new Callback<InsertProductModel>() {
                    @Override
                    public void onResponse(@NonNull Call<InsertProductModel> call, @NonNull final Response<InsertProductModel> response) {
                        if (response.isSuccessful()) {
                            InsertProductModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<InsertProductModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void deleteProduct(String id, String prod_id, String delete, final DownlodableCallback<InsertProductModel> callback) {
        createRetrofitService().deleteProduct(id, prod_id, delete).enqueue(
                new Callback<InsertProductModel>() {
                    @Override
                    public void onResponse(@NonNull Call<InsertProductModel> call, @NonNull final Response<InsertProductModel> response) {
                        if (response.isSuccessful()) {
                            InsertProductModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<InsertProductModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void uploadimage(@Nullable MultipartBody.Part body, @Nullable RequestBody name, final DownlodableCallback<Void> callback) {
        createRetrofitService().uploadImage(body,name).enqueue(
                new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull final Response<Void> response) {
                        if (response.isSuccessful()) {
                            callback.onFailure("success");
                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void getSellerOrerList(String seller_id, final DownlodableCallback<OrderHistoryModel> callback) {
        createRetrofitService().getOrderHitory(seller_id).enqueue(
                new Callback<OrderHistoryModel>() {
                    @Override
                    public void onResponse(@NonNull Call<OrderHistoryModel> call, @NonNull final Response<OrderHistoryModel> response) {
                        if (response.isSuccessful()) {
                            OrderHistoryModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);
                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<OrderHistoryModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void getrerDetails(String order_id, String address_id, final DownlodableCallback<OrderDetailsModel> callback) {
        createRetrofitService().getOrderDetaisList(order_id, address_id).enqueue(
                new Callback<OrderDetailsModel>() {
                    @Override
                    public void onResponse(@NonNull Call<OrderDetailsModel> call, @NonNull final Response<OrderDetailsModel> response) {
                        if (response.isSuccessful()) {
                            OrderDetailsModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);
                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<OrderDetailsModel> call, @NonNull Throwable t) {
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