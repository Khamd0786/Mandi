package com.hammad.jivaassisment.data

import com.hammad.jivaassisment.model.Apple
import javax.inject.Inject

interface MockService {

    fun getAppleInfo(): List<Apple>
    fun getVillages(): List<String>
    fun getPrice(village: String): Int

    class Impl @Inject constructor() : MockService {

        private val apples by lazy {
            listOf(
                Apple("Village1", 150),
                Apple("Village2", 110),
                Apple("Village3", 120),
                Apple("Village4", 130),
                Apple("Village5", 140),
                Apple("Village6", 160),
                Apple("Village7", 200),
                Apple("Village8", 70),
                Apple("Village9", 80),
            )
        }

        override fun getAppleInfo(): List<Apple> {
            return apples
        }

        override fun getVillages(): List<String> {
            return apples.map { it.village }
        }

        override fun getPrice(village: String): Int {
            return apples.filter { it.village == village }.map { it.pricePerKg }.first()
        }

    }
}