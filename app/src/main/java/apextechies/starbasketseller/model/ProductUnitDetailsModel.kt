package apextechies.starbasketseller.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ProductUnitDetailsModel(

    @SerializedName("id")
    var id: String? = null,
    @SerializedName("prod_id")
    var prod_id: String? = null,
    @SerializedName("unit")
    var unit: String? = null,
    @SerializedName("actual_price")
    var actual_price: String? = null,
    @SerializedName("selling_price")
    var selling_price: String? = null,
    @SerializedName("discount")
    var discount: String? = null,
    @SerializedName("short_description")
    var short_description: String? = null,
    @SerializedName("full_description")
    var full_description: String? = null,
    @SerializedName("created_date")
    var created_date: String? = null,
    @SerializedName("status")
    var status: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(prod_id)
        parcel.writeString(unit)
        parcel.writeString(actual_price)
        parcel.writeString(selling_price)
        parcel.writeString(discount)
        parcel.writeString(short_description)
        parcel.writeString(full_description)
        parcel.writeString(created_date)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductUnitDetailsModel> {
        override fun createFromParcel(parcel: Parcel): ProductUnitDetailsModel {
            return ProductUnitDetailsModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductUnitDetailsModel?> {
            return arrayOfNulls(size)
        }
    }
}