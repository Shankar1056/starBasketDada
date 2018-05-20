package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class LoginModel {
    @SerializedName("status")
    var status: String? = null
    @SerializedName("msg")
    var msg: String? = null
    @SerializedName("data")
    var data: ArrayList<LoginDataModel>? = null

}