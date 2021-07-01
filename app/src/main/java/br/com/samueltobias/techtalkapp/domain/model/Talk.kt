package br.com.samueltobias.techtalkapp.domain.model

import br.com.samueltobias.techtalkapp.ui.model.Talk
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class Talk(
    @SerializedName("_id")
    val id: String,
    val title: String,
    val speaker: String,
    val date: Date,
    val duration: Minutes,
    val subjects: Set<String>,
    val active: Boolean = true
) {
    fun toUiModel(): Talk {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

        return Talk(
            title = title,
            speaker = speaker,
            date = formatter.format(date),
            duration = duration.value,
            subjects = subjects,
            active = active
        )
    }
}
