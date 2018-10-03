package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class CategoryDateModel {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("icon")
    var icon: String? = null
    @SerializedName("status")
    var status: String? = null
}