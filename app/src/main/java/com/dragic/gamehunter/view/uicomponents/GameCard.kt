package com.dragic.gamehunter.view.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.theme.Typography

@Composable
fun GameCard(
    gameTitle: String,
    thumbnail: String,
    onGameClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Image(
                modifier = Modifier.clickable { onGameClick() },
                painter = painterResource(id = R.drawable.random_thumbnail),
                contentDescription = stringResource(id = R.string.favorite_game_content_description),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        top = dimensionResource(id = R.dimen.deal_card_title_top_margin),
                        start = dimensionResource(id = R.dimen.deal_card_title_horizontal_padding),
                        end = dimensionResource(id = R.dimen.deal_card_title_horizontal_padding),
                    ),
                text = gameTitle,
                style = Typography.bodyMedium
            )
        }
    }
}
