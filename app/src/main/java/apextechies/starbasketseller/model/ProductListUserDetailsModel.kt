package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class ProductListUserDetailsModel {

    @SerializedName("name")
    var name: String? = null
    @SerializedName("mobile_number")
    var mobile_number: String? = null
    @SerializedName("email_id")
    var email_id: String? = null
    @SerializedName("business_name")
    var business_name: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("created_date")
    var created_date: String? = null

}