package com.hammad.jivaassisment.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hammad.jivaassisment.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: User)

    @Query("SELECT * FROM user WHERE user_name=:userName ")
    fun getUserByName(userName: String) : User?

    @Query("SELECT * FROM user WHERE loyalty_id=:loyaltyId ")
    fun getUserByLoyaltyId(loyaltyId: String) : User?



}