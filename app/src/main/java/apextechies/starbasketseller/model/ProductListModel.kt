package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class ProductListModel {
    @SerializedName("status")
    var status: String? = null
    @SerializedName("msg")
    var msg: String? = null
    @SerializedName("data")
    var data: ArrayList<ProductListDataModel>? = null
}