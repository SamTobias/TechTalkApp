package br.com.samueltobias.techtalkapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.samueltobias.techtalkapp.R
import br.com.samueltobias.techtalkapp.ui.model.Talk
import br.com.samueltobias.techtalkapp.ui.theme.PantoneYellow
import br.com.samueltobias.techtalkapp.ui.theme.TechTalkAppTheme

@Composable
fun ListScreen(talksLiveData: LiveData<List<Talk>>, onRefresh: () -> Unit) {
    val talks by talksLiveData.observeAsState(initial = emptyList())

    Scaffold(
        backgroundColor = MaterialTheme.colors.primarySurface,
        topBar = { AppBar(onRefresh) }) {
        TalksList(talks)
    }
}

@Composable
private fun AppBar(onRefresh: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Tech Talks",
            Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.h5
        )
        IconButton(
            modifier = Modifier.padding(16.dp),
            onClick = onRefresh
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(R.string.reload)
            )
        }
    }
}

@Composable
fun TalksList(talks: List<Talk>) = LazyColumn {
    items(talks) { talk ->
        TalkCard(talk = talk)
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun TalkCard(talk: Talk) {
    var visible by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
        onClick = { if (talk.subjects.isNotEmpty()) visible = !visible }
    ) {
        Column {
            val typography = MaterialTheme.typography
            Image(
                painter = painterResource(id = R.drawable.hero),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.DarkGray)
                    .height(180.dp)
                    .fillMaxWidth()
            )
            Column(Modifier.padding(16.dp)) {
                Text(text = talk.title, style = typography.h6)
                Text(text = talk.speaker, style = typography.body2)
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = stringResource(R.string.date),
                        tint = PantoneYellow,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Text(text = talk.date)
                }
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.HourglassEmpty,
                        contentDescription = stringResource(R.string.duration),
                        tint = PantoneYellow,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Text(text = "${talk.duration} min")
                }

                if (talk.subjects.isNotEmpty()) {
                    AnimatedVisibility(visible = visible) {
                        Column {
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = stringResource(R.string.subjects),
                                style = typography.body2
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Row {
                                talk.subjects.forEach { subject ->
                                    Chip(subject)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Chip(text: String) {
    Surface(
        modifier = Modifier
            .padding(end = 10.dp, bottom = 10.dp)
            .defaultMinSize(minWidth = 30.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = PantoneYellow
        ),
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = text,
                color = PantoneYellow,
                fontSize = 12.sp,
                modifier = Modifier.padding(3.dp),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview
@Composable
fun ExampleTalkCard() {
    TechTalkAppTheme {
        TalkCard(
            talk = Talk(
                title = "Conhecendo o desenvolvimento Android",
                speaker = "Samuel Tobias",
                date = "01/07/2021 16:00",
                duration = 60,
                subjects = emptySet()
            )
        )
    }
}

@Preview(name = "Exemplo em inglês com fonte maior", fontScale = 1.5f, locale = "en")
@Composable
fun ExampleTalkCardWithSubjects() {
    TechTalkAppTheme {
        TalkCard(
            talk = Talk(
                title = "Conhecendo o desenvolvimento Android",
                speaker = "Samuel Tobias",
                date = "01/07/2021 16:00",
                duration = 60,
                subjects = setOf("Android", "Kotlin", "Jetpack Compose")
            )
        )
    }
}

@Preview
@Composable
fun ListScreenExample() {
    TechTalkAppTheme {
        val liveData = MutableLiveData(
            listOf(
                Talk(
                    title = "Conhecendo o desenvolvimento Android",
                    speaker = "Samuel Tobias",
                    date = "01/07/2021 16:00",
                    duration = 60,
                    subjects = setOf("Android", "Kotlin", "Jetpack Compose")
                ),
            )
        )
        ListScreen(liveData) { }
    }
}