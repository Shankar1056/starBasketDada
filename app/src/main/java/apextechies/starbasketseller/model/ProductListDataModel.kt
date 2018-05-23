package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class ProductListDataModel {

    @SerializedName("id")
var id: String?= null
    @SerializedName("sub_cat_id")
    var sub_cat_id: String?= null
    @SerializedName("sub_sub_cat_id")
    var sub_sub_cat_id: String?= null
    @SerializedName("name")
    var name: String?= null
    @SerializedName("created_date")
    var created_date: String?= null
    @SerializedName("sellerdetails")
    var sellerdetails: ProductListUserDetailsModel?= null
    @SerializedName("unitdetails")
    var unitdetails: ArrayList<ProductUnitDetailsModel>?= null
}