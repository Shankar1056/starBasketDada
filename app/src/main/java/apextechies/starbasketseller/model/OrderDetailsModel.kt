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
    @SerializedName("status")
    var status: String?= null
    @SerializedName("adress")
    var adress: ArrayList<AddressModel>?= null

}

class AddressModel {

    @SerializedName("id")
    var id: String?= null
    @SerializedName("state_id")
    var state_id: String?= null
    @SerializedName("user_id")
    var user_id: String?= null
    @SerializedName("pincode")
    var pincode: String?= null
    @SerializedName("address1")
    var address1: String?= null
    @SerializedName("address2")
    var address2: String?= null
    @SerializedName("name")
    var name: String?= null
    @SerializedName("city")
    var city: String?= null
    @SerializedName("landmark")
    var landmark: String?= null
}
