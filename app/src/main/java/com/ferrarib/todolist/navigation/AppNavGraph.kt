package com.ferrarib.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ferrarib.todolist.presentation.details.DetailsScreen
import com.ferrarib.todolist.presentation.todos.TasksScreen
import androidx.hilt.navigation.compose.hiltViewModel
import timber.log.Timber

sealed class Screens(val route: String) {
    data object Todos : Screens(route = "todo_list")
    data object TodoDetails : Screens(route = "detail")
}

sealed class Params(val value: String) {
    data object Id : Params(value = "id")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Todos.route) {
        composable(route = Screens.Todos.route) {
            TasksScreen(
                viewModel = hiltViewModel(),
                onDetailsClicked = { id ->
                    Timber.d("Opening details for id = $id")
                    navController.navigate("${Screens.TodoDetails.route}/?id=$id")
                },
                onAddNewClicked = {
                    navController.navigate("${Screens.TodoDetails.route}/?id=${null}")
                }
            )
        }

        composable(
            route = "${Screens.TodoDetails.route}/?id={${Params.Id.value}}",
            arguments = listOf(navArgument(Params.Id.value) {
                type = NavType.StringType
                nullable = true
            })
        ) { backStackEntry ->
            DetailsScreen(
                id = backStackEntry.arguments?.getString(Params.Id.value)?.toLong(),
                viewModel = hiltViewModel()
            )
        }
    }
}