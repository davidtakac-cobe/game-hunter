package com.dragic.gamehunter.view.gamedetails

data class ImageContentViewState(
    val gameTitle: String,
    val thumbnail: String,
    val lowestPrice: String,
    val dateLowestPrice: String,
    val isFavorite: Boolean,
)
