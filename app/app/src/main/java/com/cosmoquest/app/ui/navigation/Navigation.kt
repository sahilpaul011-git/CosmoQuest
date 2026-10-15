
package com.cosmoquest.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cosmoquest.app.ui.screens.courses.CoursesScreen
import com.cosmoquest.app.ui.screens.explorer.SolarSystemScreen
import com.cosmoquest.app.ui.screens.home.HomeScreen
import com.cosmoquest.app.ui.screens.planet.PlanetDetailScreen
import com.cosmoquest.app.ui.screens.profile.ProfileScreen
import com.cosmoquest.app.ui.screens.quiz.QuizScreen

object CosmoRoutes {
    const val HOME = "home"
    const val EXPLORER = "explorer"
    const val PLANET_DETAIL = "planet/{planetId}"
    const val QUIZ = "quiz"
    const val PLANET_QUIZ = "quiz/{planetId}"
    const val PROFILE = "profile"
    const val COURSES = "courses"

    fun planetDetail(planetId: String) = "planet/$planetId"
    fun planetQuiz(planetId: String) = "quiz/$planetId"
}

@Composable
fun CosmoQuestNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CosmoRoutes.HOME
    ) {
        composable(CosmoRoutes.HOME) {
            HomeScreen(
                onExploreClick = {
                    navController.navigate(CosmoRoutes.EXPLORER)
                },
                onQuizClick = {
                    navController.navigate(CosmoRoutes.QUIZ)
                },
                onProfileClick = {
                    navController.navigate(CosmoRoutes.PROFILE)
                },
                onCoursesClick = {
                    navController.navigate(CosmoRoutes.COURSES)
                }
            )
        }

        composable(CosmoRoutes.EXPLORER) {
            SolarSystemScreen(
                onPlanetClick = { planetId ->
                    navController.navigate(
                        CosmoRoutes.planetDetail(planetId)
                    )
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = CosmoRoutes.PLANET_DETAIL,
            arguments = listOf(
                navArgument("planetId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val planetId =
                backStackEntry.arguments?.getString("planetId") ?: "earth"

            PlanetDetailScreen(
                planetId = planetId,
                onBack = {
                    navController.popBackStack()
                },
                onQuizClick = {
                    navController.navigate(
                        CosmoRoutes.planetQuiz(planetId)
                    )
                }
            )
        }

        composable(CosmoRoutes.QUIZ) {
            QuizScreen(
                planetId = null,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = CosmoRoutes.PLANET_QUIZ,
            arguments = listOf(
                navArgument("planetId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val planetId =
                backStackEntry.arguments?.getString("planetId")

            QuizScreen(
                planetId = planetId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(CosmoRoutes.PROFILE) {
            ProfileScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(CosmoRoutes.COURSES) {
            CoursesScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
