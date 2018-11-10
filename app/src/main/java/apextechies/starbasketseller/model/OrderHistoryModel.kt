package apextechies.starbasketseller.model

import com.google.gson.annotations.SerializedName

class OrderHistoryModel(
        @SerializedName("status")
        var status: String? = null,
        @SerializedName("msg")
        var msg: String? = null,
        @SerializedName("data")
        var data: ArrayList<OrderHistoryDataModel>
)

class OrderHistoryDataModel {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("transaction_id")
    var transaction_id: String? = null
    @SerializedName("price")
    var price: String? = null
    @SerializedName("delivery_charge")
    var delivery_charge: String? = null
    @SerializedName("discount")
    var discount: String? = null
    @SerializedName("coupon_code")
    var coupon_code: String? = null
    @SerializedName("total_price")
    var total_price: String? = null
    @SerializedName("address_id")
    var address_id: String? = null
    @SerializedName("user_id")
    var user_id: String? = null
    @SerializedName("order_date")
    var order_date: String? = null
    @SerializedName("order_status")
    var order_status: String? = null
    @SerializedName("seller_id")
    var seller_id: String? = null
    @SerializedName("payment_type")
    var payment_type: String? = null
    @SerializedName("refund_type")
    var refund_type: String? = null
}
