package com.dragic.gamehunter.view.gamedetails

data class DealDetailsViewState(
    val dealId: String,
    val storeId: String,
    var storeName: String,
    var storeLogo: String,
    val savePercentage: String,
    val salePrice: String,
    val normalPrice: String,
)
