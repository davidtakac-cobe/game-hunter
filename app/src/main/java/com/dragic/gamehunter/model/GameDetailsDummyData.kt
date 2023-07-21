package com.dragic.gamehunter.model

import javax.inject.Inject

class GameDetailsDummyData @Inject constructor() {

    val data = GameDetailsEntity(
        info = GameInfo("Counter:Strike Global Offensive", ""),
        cheapestPrice = GameCheapestPrice("3.99 $", 1543028665),
        deals = listOf(
            Deal("123", "4123", "3.99 $", "15.99 $", "-15% OFF"),
            Deal("123", "4123", "3.99 $", "15.99 $", "-15% OFF"),
            Deal("123", "4123", "3.99 $", "15.99 $", "-15% OFF"),
            Deal("123", "4123", "3.99 $", "15.99 $", "-15% OFF"),
            Deal("123", "4123", "3.99 $", "15.99 $", "-15% OFF"),
            Deal("123", "4123", "3.99 $", "15.99 $", "-15% OFF"),
        ),
    )
}
