package com.hammad.jivaassisment.repository

import com.hammad.jivaassisment.data.MockService
import com.hammad.jivaassisment.db.dao.UserDao
import com.hammad.jivaassisment.model.Apple
import com.hammad.jivaassisment.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CoreRepository {

    fun registerUser(userName: String, loyaltyId: String, multiplier: Double)
    fun getUserByName(userName: String): Flow<User?>
    fun getUserById(id: String): Flow<User?>

    fun getVillages(): List<String>
    fun getPrice(village: String): Int
    fun getAppleInfo(): List<Apple>

    class Impl @Inject constructor(private val db: UserDao, private val mockService: MockService) : CoreRepository {

        override fun registerUser(userName: String, loyaltyId: String, multiplier: Double) {
            val user = User(userName, loyaltyId, multiplier)
            db.insert(user)
        }

        override fun getUserByName(userName: String): Flow<User?> = flow {
            val user = db.getUserByName(userName)
            emit(user)
        }

        override fun getUserById(id: String): Flow<User?>  = flow {
            val user = db.getUserByLoyaltyId(id)
            emit(user)
        }

        override fun getVillages(): List<String> {
            return mockService.getVillages()
        }

        override fun getPrice(village: String): Int {
            return mockService.getPrice(village)
        }

        override fun getAppleInfo(): List<Apple> {
            return mockService.getAppleInfo()
        }


    }
}