package com.example.kotlinmvpespresso.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by sally on 7/7/17.
 */
class RepositoryModel : Parcelable {
    @SerializedName("id")
    var id: Int = 0
        private set
    @SerializedName("name")
    var name: String? = null
        private set get
    @SerializedName("full_name")
    var fullName: String? = null
        private set
    @SerializedName("owner")
    var ownerInfo: OwnerModel? = null
        private set
    @SerializedName("private")
    var isPrivate: Boolean = false
        private set
    @SerializedName("html_url")
    var htmlUrl: String? = null
        private set
    @SerializedName("description")
    var description: String? = null
        private set
    @SerializedName("fork")
    var isFork: Boolean = false
        private set
    @SerializedName("url")
    var url: String? = null
        private set
    @SerializedName("created_at")
    private var createdAt: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
    @SerializedName("language")
    var language: String? = null
        private set
    @SerializedName("has_wiki")
    var isHasWiki: Boolean = false
        private set

    constructor(id: Int, name: String, fullName: String, ownerModel: OwnerModel, isPrivate: Boolean, htmlUrl: String, description: String, isFork: Boolean, url: String, createdAt: String, updatedAt: String, language: String, hasWiki: Boolean) {
        this.id = id
        this.name = name
        this.fullName = fullName
        this.ownerInfo = ownerModel
        this.isPrivate = isPrivate
        this.htmlUrl = htmlUrl
        this.description = description
        this.isFork = isFork
        this.url = url
        this.createdAt = createdAt
        this.updatedAt = updatedAt
        this.language = language
        this.isHasWiki = hasWiki
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        name = `in`.readString()
        fullName = `in`.readString()
        isPrivate = `in`.readByte().toInt() != 0
        htmlUrl = `in`.readString()
        description = `in`.readString()
        isFork = `in`.readByte().toInt() != 0
        url = `in`.readString()
        createdAt = `in`.readString()
        updatedAt = `in`.readString()
        language = `in`.readString()
        isHasWiki = `in`.readByte().toInt() != 0
        ownerInfo = `in`.readParcelable<OwnerModel>(OwnerModel::class.java.getClassLoader())
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(fullName)
        dest.writeByte((if (isPrivate) 1 else 0).toByte())
        dest.writeString(htmlUrl)
        dest.writeString(description)
        dest.writeByte((if (isFork) 1 else 0).toByte())
        dest.writeString(url)
        dest.writeString(createdAt)
        dest.writeString(updatedAt)
        dest.writeString(language)
        dest.writeByte((if (isHasWiki) 1 else 0).toByte())
        dest.writeParcelable(ownerInfo, flags)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RepositoryModel> = object : Parcelable.Creator<RepositoryModel> {
            override fun createFromParcel(`in`: Parcel): RepositoryModel {
                return RepositoryModel(`in`)
            }

            override fun newArray(size: Int): Array<RepositoryModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
