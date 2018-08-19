package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class OrderHistoryModel (
    @SerializedName("status")
var status: String?= null,
    @SerializedName("msg")
    var msg: String?= null,
@SerializedName("data")
var data: ArrayList<OrderHistoryDataModel>
)

class OrderHistoryDataModel(
@SerializedName("id")
var id: String?= null,
@SerializedName("product_id")
var product_id: String?= null,
@SerializedName("quantity")
var quantity: String?= null,
@SerializedName("product_name")
var product_name: String?= null,
@SerializedName("price")
var price: String?= null,
@SerializedName("unit")
var unit: String?= null,
@SerializedName("image")
var image: String?= null,
@SerializedName("seller_id")
var seller_id: String?= null,
@SerializedName("order_date")
var order_date: String?= null,
@SerializedName("order_status")
var order_status: String?= null
)
