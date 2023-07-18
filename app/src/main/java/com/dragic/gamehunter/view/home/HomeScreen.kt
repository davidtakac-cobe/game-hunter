package com.dragic.gamehunter.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dragic.gamehunter.R
import com.dragic.gamehunter.model.DealDummyData
import com.dragic.gamehunter.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onDealClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = HomeViewModel(DealDummyData()),
) {
    Column {
        Spacer(modifier = Modifier.size(size = 20.dp))
        Text(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.home_top_deals_margin),
                    end = dimensionResource(id = R.dimen.home_top_deals_margin)
                ),
            text = stringResource(id = R.string.top_deals),
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.size(8.dp))
        HomeDeals(
            deals = homeViewModel.dealData,
            onDealClick = { id ->
                onDealClick(id)
            },
            modifier = modifier.fillMaxWidth(),
        )
    }
}
