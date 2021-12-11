package com.bielfernandezb.todoapp.domain.entity

import android.os.Parcel
import android.os.Parcelable
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
    var title: String,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "completed")
    var completed: Boolean,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    constructor(title: String, description: String?) : this(
        id = 0,
        title = title,
        description = description,
        completed = false
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(entryId)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeByte(if (completed) 1 else 0)
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