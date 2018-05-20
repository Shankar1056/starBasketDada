package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class LoginDataModel {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("email_id")
    var email: String? = null
    @SerializedName("password")
    var password: String? = null
    @SerializedName("mobile_number")
    var phone: String? = null
    @SerializedName("business_name")
    var business_name: String? = null
    @SerializedName("device_id")
    var device_id: String? = null
    @SerializedName("address")
    var address: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("created_date")
    var created_date: String? = null
}