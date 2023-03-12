package com.most4dev.domain.entities

import android.os.Parcel
import android.os.Parcelable

data class GameResultEntity(
    val currentScore: Long,
    val bestScore: Long
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(currentScore)
        parcel.writeLong(bestScore)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameResultEntity> {
        override fun createFromParcel(parcel: Parcel): GameResultEntity {
            return GameResultEntity(parcel)
        }

        override fun newArray(size: Int): Array<GameResultEntity?> {
            return arrayOfNulls(size)
        }
    }
}