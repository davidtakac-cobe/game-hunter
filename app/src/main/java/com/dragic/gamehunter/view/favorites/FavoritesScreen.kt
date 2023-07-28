package com.dragic.gamehunter.view.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.dragic.gamehunter.R
import com.dragic.gamehunter.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreen(
    onGameClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    favoritesViewModel: FavoritesViewModel,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.favorites_text_top_padding)))
        Text(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.favorites_text_label_top_padding),
                    end = dimensionResource(id = R.dimen.favorites_text_label_end_padding)
                ),
            text = stringResource(id = R.string.favorite_games),
            style = MaterialTheme.typography.labelLarge
        )
        FavoriteGames(
            favoriteGames = favoritesViewModel.favoriteGames,
            onGameClick = onGameClick,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.favorites_game_card_horizontal_padding),
                    top = dimensionResource(id = R.dimen.favorites_game_card_top_padding),
                    end = dimensionResource(id = R.dimen.favorites_game_card_horizontal_padding),
                )
        )
    }
}
