package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class OrderDetailsModel {

    @SerializedName("status")
    var status: String?=null
    @SerializedName("msg")
    var msg: String?=null
    @SerializedName("data")
    var data: ArrayList<OrderetailsDataModel>?=null
}

class OrderetailsDataModel {

    @SerializedName("id")
    var id: String?= null
    @SerializedName("order_id")
    var order_id: String?= null
    @SerializedName("product_name")
    var product_name: String?= null
    @SerializedName("quantity")
    var quantity: String?= null
    @SerializedName("price")
    var price: String?= null
    @SerializedName("varient")
    var varient: String?= null
    @SerializedName("image")
    var image: String?= null
    @SerializedName("seller_id")
    var seller_id: String?= null
    @SerializedName("status")
    var status: String?= null

}
