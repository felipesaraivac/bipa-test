@file:OptIn(ExperimentalMaterial3Api::class)

package com.saraiva.bipa.ui.screens.rankings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.saraiva.bipa.R
import com.saraiva.bipa.core.utils.State
import com.saraiva.bipa.domain.entity.NodeEntity
import com.saraiva.bipa.ui.screens.rankings.components.CardHorizontalRanking
import com.saraiva.bipa.ui.components.ProgressBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RankingListScreen(
    modifier: Modifier = Modifier,
    viewModel: RankingViewModel = hiltViewModel()
) {
    val state = viewModel.nodes.collectAsState(initial = State.loading()).value
    val refreshState = viewModel.refreshing.collectAsState(false).value
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.screen_ranking))
                },
                actions = {
                    IconButton(onClick = { viewModel.sort() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.Sort,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            PullToRefreshBox(
                state = rememberPullToRefreshState(),
                isRefreshing = refreshState,
                onRefresh = { viewModel.refresh() }
            ) {
                RankingContent(
                    modifier,
                    state,
                )
            }
        }
    }
}

@Composable
fun RankingContent(
    modifier: Modifier = Modifier,
    state: State<List<NodeEntity>>,
    onRefresh: () -> Unit = {},
) {
    when (state) {
        is State.Failed -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(stringResource(R.string.data_could_not_be_loaded))
            }
        }

        is State.Loading -> ProgressBar()

        is State.Success -> {
            RankingList(
                modifier = modifier,
                nodes = state.data,
            )
        }
    }
}

@Composable
fun RankingList(
    nodes: List<NodeEntity>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(nodes) { index, node ->
            CardHorizontalRanking(node = node, position = index + 1)
        }
    }
}