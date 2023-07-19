package com.dragic.gamehunter.view.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.theme.Typography

@Composable
fun ImageContent(
    gameTitle: String,
    lowestPrice: String,
    dateLowestPrice: String,
    isFavorite: Boolean,
    onFavoriteSelected: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(148.dp))
        Text(
            text = gameTitle,
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.details_title_text_horizontal_margin))
        )
        Spacer(modifier = Modifier.size(4.dp))
        Row() {
            Text(
                text = stringResource(id = R.string.lowest_price_text),
                style = Typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.details_title_text_horizontal_margin))
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = lowestPrice,
                style = Typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = stringResource(id = R.string.details_text_on),
                style = Typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = dateLowestPrice,
                style = Typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Box(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.favorite_icon_start_margin),
                    bottom = dimensionResource(id = R.dimen.favorite_icon_bottom_margin)
                )
                .size(dimensionResource(id = R.dimen.favorite_box_size))
                .clip(CircleShape)
                .background(color = Color.White)
                .clickable { onFavoriteSelected() }
        ) {
            Image(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                contentDescription = stringResource(id = R.string.favorite_icon),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun ImageContentPreview() {
    ImageContent(
        gameTitle = "Counter:Strike Global Offensive",
        lowestPrice = "3.99 $",
        dateLowestPrice = "14/7/2023",
        isFavorite = false,
        onFavoriteSelected = { }
    )
}
