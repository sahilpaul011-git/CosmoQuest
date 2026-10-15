
package com.cosmoquest.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import com.cosmoquest.app.data.local.UserPreferences

private val ProfileBackground = Color(0xFF07132E)
private val ProfileCard = Color(0xFF111D3A)
private val ProfileAccent = Color(0xFF9B7BFF)
private val ProfileText = Color(0xFFF4F6FF)

@Composable
fun ProfileScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val preferences = remember {
        UserPreferences(context.applicationContext)
    }

    var totalXp by remember {
        mutableStateOf(preferences.totalXp)
    }

    var completedQuizzes by remember {
        mutableStateOf(preferences.completedQuizzes)
    }

    var currentStreak by remember {
        mutableStateOf(preferences.currentStreak)
    }

    var realisticMode by remember {
        mutableStateOf(preferences.realisticMode)
    }

    var showResetConfirmation by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ProfileBackground)
            .padding(20.dp)
    ) {
        Text(
            text = "← Back",
            color = Color(0xFF64D9FF),
            fontSize = 16.sp,
            modifier = Modifier
                .background(ProfileBackground)
                .padding(vertical = 8.dp)
                .then(
                    Modifier
                )
        )

  
        // Back navigation is provided by the button below.
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Return to Home")
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Your Space Profile",
            color = ProfileText,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Every great explorer starts with curiosity.",
            color = Color(0xFFD0D8F0),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = ProfileCard
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF29205C),
                                Color(0xFF111D3A)
                            )
                        )
                    )
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier
                        .background(ProfileAccent, CircleShape)
                        .padding(20.dp)
                ) {
                    Text(
                        text = "🚀",
                        fontSize = 38.sp
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Space Explorer",
                    color = ProfileText,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Keep learning. Keep exploring.",
                    color = Color(0xFFD0D8F0),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            StatCard(
                title = "Total XP",
                value = totalXp.toString(),
                emoji = "⭐",
                modifier = Modifier.weight(1f)
            )

            StatCard(
                title = "Quizzes",
                value = completedQuizzes.toString(),
                emoji = "🧠",
                modifier = Modifier.weight(1f)
            )

            StatCard(
                title = "Day streak",
                value = currentStreak.toString(),
                emoji = "🔥",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Learning settings",
            color = ProfileText,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = ProfileCard
            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Realistic mode",
                            color = ProfileText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "Prefer more science-focused planet information.",
                            color = Color(0xFFD0D8F0),
                            fontSize = 13.sp
                        )
                    }

                    Switch(
                        checked = realisticMode,
                        onCheckedChange = { enabled ->
                            realisticMode = enabled
                            preferences.realisticMode = enabled
                        }
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 14.dp),
                    color = Color(0xFF253252)
                )

                Text(
                    text = "Progress is stored on this device.",
                    color = Color(0xFFD0D8F0),
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                showResetConfirmation = !showResetConfirmation
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                text = if (showResetConfirmation) {
                    "Cancel reset"
                } else {
                    "Reset learning progress"
                }
            )
        }

        if (showResetConfirmation) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "This will clear your saved XP, quiz count, and streak.",
                color = Color(0xFFFF929E),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    preferences.resetProgress()
                    totalXp = preferences.totalXp
                    completedQuizzes = preferences.completedQuizzes
                    currentStreak = preferences.currentStreak
                    showResetConfirmation = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirm reset")
            }
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    emoji: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = ProfileCard
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = emoji,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = value,
                color = ProfileText,
                fontSize = 21.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = title,
                color = Color(0xFFD0D8F0),
                fontSize = 11.sp
            )
        }
    }
}
