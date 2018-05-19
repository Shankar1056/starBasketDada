package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class SubCategoryDateModel{
    @SerializedName("id")
    var id: String? = null
    @SerializedName("cat_id")
    var cat_id: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("icon")
    var icon: String? = null
}