package com.bielfernandezb.todoapp.model.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "entryid")
    var entryId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "title")
    var mTitle: String,

    @Nullable
    @ColumnInfo(name = "description")
    var mDescription: String?,

    @ColumnInfo(name = "completed")
    var mCompleted: Boolean,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    constructor(title: String, description: String?) : this(
        id = 0,
        mTitle = title,
        mDescription = description,
        mCompleted = false
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(entryId)
        parcel.writeString(mTitle)
        parcel.writeString(mDescription)
        parcel.writeByte(if (mCompleted) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}