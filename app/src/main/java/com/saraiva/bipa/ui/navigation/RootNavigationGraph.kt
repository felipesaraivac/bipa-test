package com.saraiva.bipa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saraiva.bipa.ui.screens.rankings.RankingListScreen
import com.saraiva.bipa.ui.screens.rankings.RankingViewModel

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.RANKINGS
    ) {
        composable(route = Graph.RANKINGS) {
            RankingListScreen()
        }
    }

}

object Graph {
    const val ROOT = "root_graph"
    const val RANKINGS = "rankings"
}