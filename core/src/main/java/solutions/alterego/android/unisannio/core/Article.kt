package solutions.alterego.android.unisannio.core

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Article(
    val id: String,
    val title: String,
    val author: String,
    val url: String,
    val body: String,
    val date: String,
    val source: String
) : Parcelable
