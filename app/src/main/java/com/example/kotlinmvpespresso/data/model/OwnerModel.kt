package com.example.kotlinmvpespresso.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by sally on 7/7/17.
 */
data class OwnerModel(@SerializedName("id")
                      var id: Int, @SerializedName("login")
                      var login: String?, @SerializedName("avatar_url")
                      var avatarUrl: String?, @SerializedName("gravatar_id")
                      var gravatarId: String?, @SerializedName("url")
                      var url: String?, @SerializedName("type")
                      var type: String?, @SerializedName("site_admin")
                      var siteAdmin: Boolean, @SerializedName("name")
                      var ownerName: String?, @SerializedName("blog")
                      var ownerBlog: String?, @SerializedName("email")
                      var ownerEmail: String?) : Parcelable {

    constructor(parcel: Parcel) : this(0, null, null, null, null, null, false, null, null, null) {
        id = parcel.readInt()
        login = parcel.readString()
        avatarUrl = parcel.readString()
        gravatarId = parcel.readString()
        url = parcel.readString()
        type = parcel.readString()
        siteAdmin = parcel.readByte() != 0.toByte()
        ownerName = parcel.readString()
        ownerBlog = parcel.readString()
        ownerEmail = parcel.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(login)
        dest.writeString(avatarUrl)
        dest.writeString(gravatarId)
        dest.writeString(url)
        dest.writeString(type)
        dest.writeByte((if (siteAdmin) 1 else 0).toByte())
        dest.writeString(ownerName)
        dest.writeString(ownerBlog)
        dest.writeString(ownerEmail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OwnerModel> {
        override fun createFromParcel(parcel: Parcel): OwnerModel {
            return OwnerModel(parcel)
        }

        override fun newArray(size: Int): Array<OwnerModel?> {
            return arrayOfNulls(size)
        }
    }
}