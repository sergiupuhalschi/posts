package com.sergiupuhalschi.posts.domain.models

import android.os.Parcel
import android.os.Parcelable
import com.sergiupuhalschi.posts.data.models.Post
import com.sergiupuhalschi.posts.domain.utils.UNKNOWN_ID

data class PostDto(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(userId)
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostDto> {
        override fun createFromParcel(parcel: Parcel): PostDto {
            return PostDto(parcel)
        }

        override fun newArray(size: Int): Array<PostDto?> {
            return arrayOfNulls(size)
        }
    }
}

fun Post.toDto() = PostDto(
    userId = userId ?: UNKNOWN_ID,
    id = id ?: UNKNOWN_ID,
    title = title ?: "",
    body = body ?: ""
)