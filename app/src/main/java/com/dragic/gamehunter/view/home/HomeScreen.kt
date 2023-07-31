package com.dragic.gamehunter.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.navigation.Home
import com.dragic.gamehunter.view.uicomponents.TopBar
import com.dragic.gamehunter.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onDealClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    dialogState: Boolean,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column {
            TopBar(currentScreenRoute = Home.route, onSortCLicked = { homeViewModel.setShowDialog(true) }) { }
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.home_text_padding)))
            Text(
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.home_top_deals_label_horizontal_padding),
                    ),
                text = stringResource(id = R.string.top_deals),
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.text_padding_medium)))
            HomeDeals(
                deals = homeViewModel.dealData,
                onDealClick = { id ->
                    onDealClick(id)
                },
                modifier = modifier.fillMaxWidth(),
            )
        }
        if (dialogState) {
            Dialog(onDismissRequest = { homeViewModel.setShowDialog(false) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.home_dialog_box_padding))
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(id = R.string.home_dialog_sort_by),
                            style = MaterialTheme.typography.labelLarge,
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.text_padding_medium)))
                        Text(
                            text = stringResource(id = R.string.home_dialog_deal_rating),
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.text_padding_small))
                                .clickable {
                                    homeViewModel.fetchDealsByDealRating()
                                    homeViewModel.setShowDialog(false)
                                },
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.text_padding_medium)))
                        Text(
                            text = stringResource(id = R.string.home_dialog_savings),
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.text_padding_small))
                                .clickable {
                                    homeViewModel.fetchDealsBySavings()
                                    homeViewModel.setShowDialog(false)
                                }
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.text_padding_medium)))
                        Text(
                            text = stringResource(id = R.string.home_dialog_reviews),
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.text_padding_small))
                                .clickable {
                                    homeViewModel.fetchDealsByReviews()
                                    homeViewModel.setShowDialog(false)
                                }
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.text_padding_medium)))
                    }
                }
            }
        }
    }
}
