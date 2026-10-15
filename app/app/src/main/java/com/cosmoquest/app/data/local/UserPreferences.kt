
package com.cosmoquest.app.data.local

import android.content.Context

class UserPreferences(context: Context) {

    private val preferences = context.getSharedPreferences(
        "cosmoquest_preferences",
        Context.MODE_PRIVATE
    )

    var totalXp: Int
        get() = preferences.getInt(KEY_TOTAL_XP, 0)
        set(value) {
            preferences.edit().putInt(KEY_TOTAL_XP, value.coerceAtLeast(0)).apply()
        }

    var completedQuizzes: Int
        get() = preferences.getInt(KEY_COMPLETED_QUIZZES, 0)
        set(value) {
            preferences.edit()
                .putInt(KEY_COMPLETED_QUIZZES, value.coerceAtLeast(0))
                .apply()
        }

    var currentStreak: Int
        get() = preferences.getInt(KEY_CURRENT_STREAK, 0)
        set(value) {
            preferences.edit()
                .putInt(KEY_CURRENT_STREAK, value.coerceAtLeast(0))
                .apply()
        }

    var realisticMode: Boolean
        get() = preferences.getBoolean(KEY_REALISTIC_MODE, true)
        set(value) {
            preferences.edit().putBoolean(KEY_REALISTIC_MODE, value).apply()
        }

    var lastStudiedPlanet: String
        get() = preferences.getString(KEY_LAST_PLANET, "earth") ?: "earth"
        set(value) {
            preferences.edit().putString(KEY_LAST_PLANET, value).apply()
        }

    fun addXp(amount: Int) {
        if (amount > 0) {
            totalXp = totalXp + amount
        }
    }

    fun recordCompletedQuiz(earnedXp: Int) {
        completedQuizzes = completedQuizzes + 1
        addXp(earnedXp)
    }

    fun resetProgress() {
        preferences.edit()
            .remove(KEY_TOTAL_XP)
            .remove(KEY_COMPLETED_QUIZZES)
            .remove(KEY_CURRENT_STREAK)
            .apply()
    }

    companion object {
        private const val KEY_TOTAL_XP = "total_xp"
        private const val KEY_COMPLETED_QUIZZES = "completed_quizzes"
        private const val KEY_CURRENT_STREAK = "current_streak"
        private const val KEY_REALISTIC_MODE = "realistic_mode"
        private const val KEY_LAST_PLANET = "last_studied_planet"
    }
}
