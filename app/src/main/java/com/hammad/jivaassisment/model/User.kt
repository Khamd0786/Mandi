package com.hammad.jivaassisment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user", primaryKeys = ["user_name", "loyalty_id"])
data class User(
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "loyalty_id") val loyaltyId: String,
    @ColumnInfo(name = "multiplier") val multiplier: Double
)
