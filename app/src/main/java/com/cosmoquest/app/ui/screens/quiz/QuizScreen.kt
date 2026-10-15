
package com.cosmoquest.app.ui.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import com.cosmoquest.app.data.model.QuizQuestion
import com.cosmoquest.app.data.repository.SolarSystemRepository

private val QuizBackground = Color(0xFF07132E)
private val QuizCard = Color(0xFF111D3A)
private val QuizAccent = Color(0xFF9B7BFF)
private val QuizText = Color(0xFFF4F6FF)

@Composable
fun QuizScreen(
    planetId: String?,
    onBack: () -> Unit
) {
    val questions = remember(planetId) {
        if (planetId == null) {
            SolarSystemRepository.quizQuestions
        } else {
            SolarSystemRepository.getQuizForPlanet(planetId)
        }
    }

    var questionIndex by remember(planetId) {
        mutableIntStateOf(0)
    }
    var selectedAnswer by remember(planetId) {
        mutableIntStateOf(-1)
    }
    var score by remember(planetId) {
        mutableIntStateOf(0)
    }
    var finished by remember(planetId) {
        mutableStateOf(false)
    }
    val answeredQuestionIds = remember(planetId) {
        mutableStateListOf<String>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(QuizBackground)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "← Back",
                color = Color(0xFF64D9FF),
                fontSize = 16.sp,
                modifier = Modifier.clickable { onBack() }
            )

            Text(
                text = "COSMO QUIZ",
                color = QuizAccent,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        when {
            questions.isEmpty() -> {
                Text(
                    text = "No questions available yet.",
                    color = QuizText,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = onBack) {
                    Text("Go Back")
                }
            }

            finished -> {
                QuizResultContent(
                    score = score,
                    total = questions.size,
                    onRetry = {
                        questionIndex = 0
                        selectedAnswer = -1
                        score = 0
                        finished = false
                        answeredQuestionIds.clear()
                    },
                    onBack = onBack
                )
            }

            else -> {
                val question = questions[questionIndex]

                Text(
                    text = if (planetId == null) {
                        "Solar System Challenge"
                    } else {
                        "${question.planetId.replaceFirstChar { it.uppercase() }} Challenge"
                    },
                    color = QuizText,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Question ${questionIndex + 1} of ${questions.size}",
                    color = Color(0xFFD0D8F0),
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                LinearProgressIndicator(
                    progress = {
                        (questionIndex + 1).toFloat() / questions.size
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = QuizAccent,
                    trackColor = Color(0xFF253252)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = QuizCard
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = question.question,
                            color = QuizText,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 29.sp
                        )

                        Spacer(modifier = Modifier.height(22.dp))

                        question.options.forEachIndexed { index, option ->
                            val isSelected = selectedAnswer == index
                            val isCorrect =
                                index == question.correctAnswerIndex

                            val optionColor = when {
                                selectedAnswer != -1 && isCorrect ->
                                    Color(0xFF185C46)

                                isSelected && !isCorrect ->
                                    Color(0xFF71313E)

                                isSelected ->
                                    QuizAccent

                                else ->
                                    Color(0xFF202D4D)
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp)
                                    .clickable {
                                        if (selectedAnswer == -1) {
                                            selectedAnswer = index

                                            if (
                                                index ==
                                                question.correctAnswerIndex
                                            ) {
                                                score++
                                            }

                                            answeredQuestionIds.add(
                                                question.id
                                            )
                                        }
                                    },
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = optionColor
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp),
                                    verticalAlignment =
                                        Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${'A' + index}.",
                                        color = QuizText,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )

                                    Spacer(
                                        modifier = Modifier
                                            .height(1.dp)
                                            .padding(horizontal = 6.dp)
                                    )

                                    Text(
                                        text = option,
                                        color = QuizText,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                        }

                        if (selectedAnswer != -1) {
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = if (
                                    selectedAnswer ==
                                    question.correctAnswerIndex
                                ) {
                                    "✅ Correct answer!"
                                } else {
                                    "❌ Not quite! Correct answer: " +
                                        question.options[
                                            question.correctAnswerIndex
                                        ]
                                },
                                color = if (
                                    selectedAnswer ==
                                    question.correctAnswerIndex
                                ) {
                                    Color(0xFF65E6B0)
                                } else {
                                    Color(0xFFFF929E)
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = question.explanation,
                                color = Color(0xFFD0D8F0),
                                fontSize = 14.sp,
                                lineHeight = 21.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (selectedAnswer != -1) {
                            if (questionIndex < questions.lastIndex) {
                                questionIndex++
                                selectedAnswer = -1
                            } else {
                                finished = true
                            }
                        }
                    },
                    enabled = selectedAnswer != -1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = if (
                            questionIndex == questions.lastIndex
                        ) {
                            "See My Results 🏆"
                        } else {
                            "Next Question →"
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun QuizResultContent(
    score: Int,
    total: Int,
    onRetry: () -> Unit,
    onBack: () -> Unit
) {
    val percentage = if (total > 0) score * 100 / total else 0
    val xp = score * 10

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🏆",
            fontSize = 70.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Mission Complete!",
            color = QuizText,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "$score / $total correct",
            color = Color(0xFF64D9FF),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "$percentage% accuracy",
            color = Color(0xFFD0D8F0),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = QuizCard
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "+$xp XP",
                    color = Color(0xFFFFD166),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "Experience earned in this quiz",
                    color = QuizText,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRetry,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Try Again")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Return")
        }
    }
}
