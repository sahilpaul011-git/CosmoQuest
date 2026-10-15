
package com.cosmoquest.app.ui.screens.courses

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment

private val CourseBackground = Color(0xFF07132E)
private val CourseCard = Color(0xFF111D3A)
private val CourseText = Color(0xFFF4F6FF)
private val CourseAccent = Color(0xFF64D9FF)

private data class SpaceCourse(
    val title: String,
    val subtitle: String,
    val emoji: String,
    val lessons: Int,
    val difficulty: String,
    val description: String
)

private val spaceCourses = listOf(
    SpaceCourse(
        title = "Our Solar System",
        subtitle = "Meet our cosmic neighbours",
        emoji = "🪐",
        lessons = 8,
        difficulty = "Beginner",
        description = "Discover the Sun, eight planets, moons, and their orbits."
    ),
    SpaceCourse(
        title = "Stars and Galaxies",
        subtitle = "Explore the deep universe",
        emoji = "🌌",
        lessons = 6,
        difficulty = "Beginner",
        description = "Learn how stars form and how galaxies are organised."
    ),
    SpaceCourse(
        title = "Black Holes",
        subtitle = "Beyond the event horizon",
        emoji = "🕳️",
        lessons = 5,
        difficulty = "Intermediate",
        description = "Explore gravity, event horizons, and what scientists know."
    ),
    SpaceCourse(
        title = "Space Exploration",
        subtitle = "Humanity beyond Earth",
        emoji = "🚀",
        lessons = 7,
        difficulty = "Intermediate",
        description = "Discover rockets, space stations, rovers, and missions."
    ),
    SpaceCourse(
        title = "Search for Life",
        subtitle = "Are we alone?",
        emoji = "👽",
        lessons = 4,
        difficulty = "Beginner",
        description = "Explore habitable worlds and the search for life beyond Earth."
    )
)

@Composable
fun CoursesScreen(
    onBack: () -> Unit
) {
    var expandedCourse by remember {
        mutableIntStateOf(-1)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CourseBackground)
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        Text(
            text = "← Back",
            color = CourseAccent,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .background(CourseBackground)
        )

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Return to Home")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Space Academy 🎓",
            color = CourseText,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Choose a mission. Learn something extraordinary.",
            color = Color(0xFFD0D8F0),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .weight(1f, fill = true)
                .background(CourseBackground)
        ) {
            androidx.compose.foundation.lazy.LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(spaceCourses.size) { index ->
                    val course = spaceCourses[index]
                    val isExpanded = expandedCourse == index

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CourseCard
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = course.emoji,
                                    fontSize = 34.sp
                                )

                                Spacer(modifier = Modifier.padding(6.dp))

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = course.title,
                                        color = CourseText,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = course.subtitle,
                                        color = CourseAccent,
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = course.description,
                                color = Color(0xFFD0D8F0),
                                fontSize = 14.sp,
                                lineHeight = 21.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${course.lessons} lessons",
                                    color = CourseText,
                                    fontSize = 12.sp
                                )

                                Text(
                                    text = course.difficulty,
                                    color = CourseAccent,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            LinearProgressIndicator(
                                progress = { 0f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(5.dp),
                                color = CourseAccent,
                                trackColor = Color(0xFF253252)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = {
                                    expandedCourse =
                                        if (isExpanded) -1 else index
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = if (isExpanded) {
                                        "Hide course details"
                                    } else {
                                        "Explore course"
                                    }
                                )
                            }

                            if (isExpanded) {
                                Spacer(
                                    modifier = Modifier.height(10.dp)
                                )

                                Text(
                                    text = "Your learning mission",
                                    color = CourseAccent,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )

                                Spacer(
                                    modifier = Modifier.height(6.dp)
                                )

                                Text(
                                    text = "Start with the basics, remember " +
                                        "the key facts, and test what you " +
                                        "learned with a quiz. Course lessons " +
                                        "and saved completion tracking will " +
                                        "be added in a future update.",
                                    color = CourseText,
                                    fontSize = 13.sp,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Keep exploring. The universe is huge!",
            color = Color(0xFFD0D8F0),
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
