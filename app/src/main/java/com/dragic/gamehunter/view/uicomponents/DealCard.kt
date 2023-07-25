package com.dragic.gamehunter.view.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.theme.CardContainerColor
import com.dragic.gamehunter.view.theme.Green
import com.dragic.gamehunter.view.theme.Typography

@Composable
fun DealCard(
    gameTitle: String,
    salePrice: String,
    normalPrice: String,
    savePercentage: String,
    steamRating: String,
    dealRating: String,
    thumbnail: String,
    onDealClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clickable { onDealClick() }
            .focusable(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.deal_card_radius)),
        colors = CardDefaults.cardColors(containerColor = CardContainerColor)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.width(dimensionResource(id = R.dimen.deal_image_width)),
                painter = painterResource(id = R.drawable.random_thumbnail),
                contentDescription = stringResource(id = R.string.deal_image_content_description),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = dimensionResource(id = R.dimen.deal_card_title_top_margin)),
                    text = gameTitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.titleSmall
                )
                Spacer(modifier = Modifier.size(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(
                            text = stringResource(id = R.string.deal_card_rating_label),
                            fontSize = dimensionResource(id = R.dimen.deal_card_text_size).value.sp
                        )
                        Text(
                            text = dealRating,
                            style = Typography.bodyMedium
                        )
                    }
                    Row(
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.deal_card_padding))
                    ) {
                        Text(
                            text = stringResource(id = R.string.deal_card_steam_rating_label),
                            fontSize = dimensionResource(id = R.dimen.deal_card_text_size).value.sp
                        )
                        Text(
                            text = steamRating,
                            style = Typography.bodyMedium
                        )
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .background(
                                color = Green,
                                shape = RectangleShape
                            )
                            .padding(horizontal = dimensionResource(id = R.dimen.deal_save_percentage_padding)),
                        text = savePercentage,
                        style = Typography.bodyMedium
                    )
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = normalPrice,
                            style = Typography.bodySmall
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = salePrice,
                            style = Typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DealCardPreview() {
    DealCard(
        gameTitle = "Counter:Strike Global Offensive",
        salePrice = "3.50 $",
        normalPrice = "5.0 $",
        savePercentage = "-15% OFF",
        steamRating = "5.0",
        dealRating = "5.0",
        thumbnail = "",
        onDealClick = { },
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.deal_card_height))
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.deal_card_padding))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.deal_card_radius)))
    )
}
