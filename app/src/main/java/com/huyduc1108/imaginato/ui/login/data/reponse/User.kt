package com.huyduc1108.imaginato.ui.login.data.reponse

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "User")
data class User(
    @PrimaryKey
    val userId: String,
    val userName: String) : Parcelable {
    lateinit var xAcc: String
}