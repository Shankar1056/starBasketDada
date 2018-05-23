package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class ProductUnitDetailsModel {

    @SerializedName("id")
    var id: String? = null
    @SerializedName("prod_id")
    var prod_id: String? = null
    @SerializedName("unit")
    var unit: String? = null
    @SerializedName("actual_price")
    var actual_price: String? = null
    @SerializedName("selling_price")
    var selling_price: String? = null
    @SerializedName("discount")
    var discount: String? = null
    @SerializedName("short_description")
    var short_description: String? = null
    @SerializedName("full_description")
    var full_description: String? = null
    @SerializedName("created_date")
    var created_date: String? = null
    @SerializedName("status")
    var status: String? = null
}