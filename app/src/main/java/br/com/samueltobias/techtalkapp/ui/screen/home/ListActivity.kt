package br.com.samueltobias.techtalkapp.ui.screen.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import br.com.samueltobias.techtalkapp.ui.theme.TechTalkAppTheme

class ListActivity : ComponentActivity() {
    private val viewModel : ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechTalkAppTheme {
                ListScreen(viewModel.talks) {
                    refresh()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        refresh()
    }

    private fun refresh() {
        viewModel.getTalks()
    }
}