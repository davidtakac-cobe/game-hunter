package com.dragic.gamehunter.model

import javax.inject.Inject

class DealDummyData @Inject constructor() {
    val deals: List<DealEntity> = listOf(
        DealEntity(1, "Counter Strike: Global Offensive", 3.5, 5.0, 15.0, 4.5, 5.0, "imageUrl"),
        DealEntity(2, "League of Legends", 4.5, 6.0, 20.0, 4.6, 6.0, "imageUrl"),
        DealEntity(3, "PUBG: Battlegrounds", 6.5, 10.0, 35.0, 4.7, 9.0, "imageUrl"),
        DealEntity(4, "Paladins", 8.5, 7.5, 5.0, 4.8, 4.0, "imageUrl"),
        DealEntity(5, "Mortal Kombat X", 14.5, 11.5, 15.0, 4.4, 1.0, "imageUrl"),
    )
}

