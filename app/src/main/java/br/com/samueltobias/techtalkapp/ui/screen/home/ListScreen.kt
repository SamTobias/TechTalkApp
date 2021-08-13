package br.com.samueltobias.techtalkapp.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.samueltobias.techtalkapp.R
import br.com.samueltobias.techtalkapp.ui.screen.home.widget.TalkCard
import br.com.samueltobias.techtalkapp.ui.model.Talk
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