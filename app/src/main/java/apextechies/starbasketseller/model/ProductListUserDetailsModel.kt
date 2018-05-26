package apextechies.starbasketseller.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ProductListUserDetailsModel(

    @SerializedName("name")
    var name: String? = null,
    @SerializedName("mobile_number")
    var mobile_number: String? = null,
    @SerializedName("email_id")
    var email_id: String? = null,
    @SerializedName("business_name")
    var business_name: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("created_date")
    var created_date: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(mobile_number)
        parcel.writeString(email_id)
        parcel.writeString(business_name)
        parcel.writeString(status)
        parcel.writeString(created_date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductListUserDetailsModel> {
        override fun createFromParcel(parcel: Parcel): ProductListUserDetailsModel {
            return ProductListUserDetailsModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductListUserDetailsModel?> {
            return arrayOfNulls(size)
        }
    }
}