package apextechies.starbasketseller.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ProductListDataModel(

    @SerializedName("id")
    var id: String? = null,
    @SerializedName("sub_cat_id")
    var sub_cat_id: String? = null,
    @SerializedName("sub_sub_cat_id")
    var sub_sub_cat_id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("created_date")
    var created_date: String? = null,
    @SerializedName("sellerdetails")
    var sellerdetails: ProductListUserDetailsModel? = null,
    @SerializedName("unitdetails")
    var unitdetails: ArrayList<ProductUnitDetailsModel>? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(ProductListUserDetailsModel::class.java.classLoader),
            parcel.createTypedArrayList(ProductUnitDetailsModel)
            ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(sub_cat_id)
        parcel.writeString(sub_sub_cat_id)
        parcel.writeString(name)
        parcel.writeString(created_date)
        parcel.writeParcelable(sellerdetails, flags)
        parcel.writeTypedList(unitdetails)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductListDataModel> {
        override fun createFromParcel(parcel: Parcel): ProductListDataModel {
            return ProductListDataModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductListDataModel?> {
            return arrayOfNulls(size)
        }
    }
}