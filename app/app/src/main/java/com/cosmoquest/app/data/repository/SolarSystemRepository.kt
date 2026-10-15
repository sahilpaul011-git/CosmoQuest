
package com.cosmoquest.app.data.repository

import com.cosmoquest.app.data.model.Planet
import com.cosmoquest.app.data.model.QuizQuestion

object SolarSystemRepository {

    val planets = listOf(
        Planet(
            id = "mercury",
            name = "Mercury",
            tagline = "The smallest planet",
            description = "Mercury is the closest planet to the Sun. Its surface is covered with craters, and it has almost no substantial atmosphere to retain heat.",
            diameterKm = 4879,
            distanceFromSunKm = 57900000,
            orbitalPeriodDays = 88.0,
            dayLengthHours = 4222.6,
            moonCount = 0,
            averageTemperatureC = 167,
            gravity = "3.7 m/s²",
            atmosphere = "A very thin exosphere",
            funFact = "A single solar day on Mercury lasts about 176 Earth days.",
            colorHex = "#A7A7A7"
        ),
        Planet(
            id = "venus",
            name = "Venus",
            tagline = "The hottest planet",
            description = "Venus has a thick carbon dioxide atmosphere and clouds of sulfuric acid. Its powerful greenhouse effect makes it hotter than Mercury.",
            diameterKm = 12104,
            distanceFromSunKm = 108200000,
            orbitalPeriodDays = 224.7,
            dayLengthHours = 2802.0,
            moonCount = 0,
            averageTemperatureC = 464,
            gravity = "8.87 m/s²",
            atmosphere = "Mostly carbon dioxide",
            funFact = "Venus rotates in the opposite direction to most planets.",
            colorHex = "#E8B878"
        ),
        Planet(
            id = "earth",
            name = "Earth",
            tagline = "Our home planet",
            description = "Earth has liquid water on its surface, a protective atmosphere, and the conditions known to support life.",
            diameterKm = 12742,
            distanceFromSunKm = 149600000,
            orbitalPeriodDays = 365.25,
            dayLengthHours = 24.0,
            moonCount = 1,
            averageTemperatureC = 15,
            gravity = "9.81 m/s²",
            atmosphere = "Mostly nitrogen and oxygen",
            funFact = "Earth is the only world currently known to support life.",
            colorHex = "#3988FF"
        ),
        Planet(
            id = "mars",
            name = "Mars",
            tagline = "The Red Planet",
            description = "Mars is a cold desert world with a reddish surface, enormous volcanoes, deep canyons, and evidence of ancient flowing water.",
            diameterKm = 6779,
            distanceFromSunKm = 227900000,
            orbitalPeriodDays = 687.0,
            dayLengthHours = 24.6,
            moonCount = 2,
            averageTemperatureC = -65,
            gravity = "3.71 m/s²",
            atmosphere = "Mostly carbon dioxide",
            funFact = "Olympus Mons on Mars is one of the largest volcanoes in the Solar System.",
            colorHex = "#E66B4E"
        ),
        Planet(
            id = "jupiter",
            name = "Jupiter",
            tagline = "King of the planets",
            description = "Jupiter is the largest planet. It is a gas giant with colorful cloud bands, powerful storms, and a famous Great Red Spot.",
            diameterKm = 139820,
            distanceFromSunKm = 778600000,
            orbitalPeriodDays = 4332.6,
            dayLengthHours = 9.9,
            moonCount = 101,
            averageTemperatureC = -110,
            gravity = "24.79 m/s²",
            atmosphere = "Mostly hydrogen and helium",
            funFact = "Jupiter's Great Red Spot is a giant storm that has lasted for centuries.",
            colorHex = "#D9A77B"
        ),
        Planet(
            id = "saturn",
            name = "Saturn",
            tagline = "The ringed giant",
            description = "Saturn is a gas giant famous for its spectacular rings, which are made mostly of ice particles, rocky debris, and dust.",
            diameterKm = 116460,
            distanceFromSunKm = 1433500000,
            orbitalPeriodDays = 10759.0,
            dayLengthHours = 10.7,
            moonCount = 274,
            averageTemperatureC = -140,
            gravity = "10.44 m/s²",
            atmosphere = "Mostly hydrogen and helium",
            funFact = "Saturn's average density is lower than that of water.",
            colorHex = "#E6D19A"
        ),
        Planet(
            id = "uranus",
            name = "Uranus",
            tagline = "The sideways planet",
            description = "Uranus is an ice giant with a blue-green appearance caused by methane in its atmosphere. Its axis is tilted dramatically.",
            diameterKm = 50724,
            distanceFromSunKm = 2872500000,
            orbitalPeriodDays = 30687.0,
            dayLengthHours = 17.2,
            moonCount = 29,
            averageTemperatureC = -195,
            gravity = "8.69 m/s²",
            atmosphere = "Hydrogen, helium, and methane",
            funFact = "Uranus rotates on its side compared with most planets.",
            colorHex = "#8BDDE0"
        ),
        Planet(
            id = "neptune",
            name = "Neptune",
            tagline = "The distant blue giant",
            description = "Neptune is a cold, distant ice giant with a deep blue appearance and some of the fastest winds measured on a planet.",
            diameterKm = 49244,
            distanceFromSunKm = 4495000000,
            orbitalPeriodDays = 60190.0,
            dayLengthHours = 16.1,
            moonCount = 16,
            averageTemperatureC = -200,
            gravity = "11.15 m/s²",
            atmosphere = "Hydrogen, helium, and methane",
            funFact = "Neptune takes about 165 Earth years to orbit the Sun.",
            colorHex = "#4169E1"
        )
    )

    val quizQuestions = listOf(
        QuizQuestion(
            id = "q1",
            planetId = "mercury",
            question = "Which planet is closest to the Sun?",
            options = listOf("Earth", "Mercury", "Mars", "Venus"),
            correctAnswerIndex = 1,
            explanation = "Mercury is the innermost planet in our Solar System."
        ),
        QuizQuestion(
            id = "q2",
            planetId = "venus",
            question = "Which planet is the hottest?",
            options = listOf("Mercury", "Mars", "Venus", "Jupiter"),
            correctAnswerIndex = 2,
            explanation = "Venus's thick atmosphere traps heat through a strong greenhouse effect."
        ),
        QuizQuestion(
            id = "q3",
            planetId = "earth",
            question = "What is Earth's natural satellite called?",
            options = listOf("Titan", "Europa", "The Moon", "Phobos"),
            correctAnswerIndex = 2,
            explanation = "Earth has one natural satellite, commonly called the Moon."
        ),
        QuizQuestion(
            id = "q4",
            planetId = "mars",
            question = "Why does Mars look reddish?",
            options = listOf("Lava oceans", "Iron-rich dust and rocks", "Blue ice", "Clouds of sulfur"),
            correctAnswerIndex = 1,
            explanation = "Iron minerals in Martian dust have oxidized, giving the surface its rusty color."
        ),
        QuizQuestion(
            id = "q5",
            planetId = "jupiter",
            question = "Which is the largest planet?",
            options = listOf("Saturn", "Neptune", "Earth", "Jupiter"),
            correctAnswerIndex = 3,
            explanation = "Jupiter is the largest planet in our Solar System."
        ),
        QuizQuestion(
            id = "q6",
            planetId = "saturn",
            question = "What is Saturn famous for?",
            options = listOf("Its rings", "Its oceans", "Its blue surface", "Its volcanoes"),
            correctAnswerIndex = 0,
            explanation = "Saturn has a spectacular ring system made mainly of ice and rocky debris."
        ),
        QuizQuestion(
            id = "q7",
            planetId = "uranus",
            question = "Which planet rotates almost on its side?",
            options = listOf("Earth", "Uranus", "Venus", "Mars"),
            correctAnswerIndex = 1,
            explanation = "Uranus has an axial tilt of about 98 degrees."
        ),
        QuizQuestion(
            id = "q8",
            planetId = "neptune",
            question = "Which planet is farthest from the Sun?",
            options = listOf("Saturn", "Uranus", "Neptune", "Jupiter"),
            correctAnswerIndex = 2,
            explanation = "Neptune is the outermost of the eight recognized planets."
        )
    )

    fun getPlanetById(id: String): Planet? {
        return planets.find { it.id == id }
    }

    fun getQuizForPlanet(planetId: String): List<QuizQuestion> {
        return quizQuestions.filter { it.planetId == planetId }
    }
}
