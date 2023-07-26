package com.dragic.gamehunter.view.gamedetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.theme.GameHunterTheme
import com.dragic.gamehunter.view.theme.Typography
import com.dragic.gamehunter.view.uicomponents.ImageContent
import com.dragic.gamehunter.view.uicomponents.ImageWithGradient
import com.dragic.gamehunter.viewmodel.GameDetailsViewModel

@Composable
fun GameDetailsScreen(
    modifier: Modifier = Modifier,
    gameDetailsViewModel: GameDetailsViewModel,
) {
    val gameData = gameDetailsViewModel.gameData
    val dealData = gameDetailsViewModel.dealData
    GameHunterTheme {
        Column {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.details_image_height))
            ) {
                if (gameData != null) {
                    ImageWithGradient(
                        thumbnail = gameData.thumbnail,
                        content = {
                            with(gameData) {
                                ImageContent(
                                    gameTitle = gameTitle,
                                    lowestPrice = lowestPrice,
                                    dateLowestPrice = dateLowestPrice,
                                    isFavorite = isFavorite,
                                    onFavoriteSelected = { gameDetailsViewModel.refreshFavoriteMovie() },
                                )
                            }
                        },
                        modifier = Modifier.matchParentSize()
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.home_top_deals_margin),
                        end = dimensionResource(id = R.dimen.home_top_deals_margin)
                    ),
                text = stringResource(id = R.string.featured_deals),
                style = Typography.labelLarge
            )
            Spacer(modifier = Modifier.size(8.dp))
            GameDetailsDeals(
                deals = dealData,
                onDealClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
