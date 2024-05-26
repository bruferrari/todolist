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
    data object Tasks : Screens(route = "tasks")
    data object TaskDetails : Screens(route = "task_detail")
}

sealed class Params(val value: String) {
    data object Id : Params(value = "id")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Tasks.route) {
        composable(route = Screens.Tasks.route) {
            TasksScreen(
                viewModel = hiltViewModel(),
                onDetailsClicked = { id ->
                    Timber.d("Opening details for id = $id")
                    navController.navigate("${Screens.TaskDetails.route}/?id=$id")
                },
                onAddNewClicked = {
                    navController.navigate("${Screens.TaskDetails.route}/?id=${null}")
                }
            )
        }

        composable(
            route = "${Screens.TaskDetails.route}/?id={${Params.Id.value}}",
            arguments = listOf(navArgument(Params.Id.value) {
                type = NavType.StringType
                nullable = true
            })
        ) { backStackEntry ->
            DetailsScreen(
                id = backStackEntry.arguments?.getString(Params.Id.value)?.toLong(),
                viewModel = hiltViewModel(),
                onSaveButtonPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}