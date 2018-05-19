package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class SubCategoryModel{
    @SerializedName("status")
    var status: String?= null
    @SerializedName("msg")
    var msg: String?= null
    @SerializedName("data")
    var data: ArrayList<SubCategoryDateModel>?= null
}