
package com.cosmoquest.app.data.model

data class Planet(
    val id: String,
    val name: String,
    val tagline: String,
    val description: String,
    val diameterKm: Long,
    val distanceFromSunKm: Long,
    val orbitalPeriodDays: Double,
    val dayLengthHours: Double,
    val moonCount: Int,
    val averageTemperatureC: Int,
    val gravity: String,
    val atmosphere: String,
    val funFact: String,
    val colorHex: String,
    val imageUrl: String = ""
)

data class QuizQuestion(
    val id: String,
    val planetId: String,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
) {
    init {
        require(options.isNotEmpty()) {
            "A quiz question must have at least one option."
        }
        require(correctAnswerIndex in options.indices) {
            "The correct answer index must point to an available option."
        }
    }
}

data class QuizResult(
    val totalQuestions: Int,
    val correctAnswers: Int,
    val earnedXp: Int
) {
    val percentage: Int
        get() = if (totalQuestions > 0) {
            (correctAnswers * 100) / totalQuestions
        } else {
            0
        }
}
