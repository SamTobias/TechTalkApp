package br.com.samueltobias.techtalkapp.data.repository

import br.com.samueltobias.techtalkapp.domain.model.Minutes
import br.com.samueltobias.techtalkapp.domain.model.Talk
import br.com.samueltobias.techtalkapp.domain.repository.TalkRepository
import java.util.*

class FakeTalkRepositoryImpl : TalkRepository {
    override suspend fun getTalks(): List<Talk> {
        return listOf(
            Talk(
                id = "aaa",
                title = "Desenvolvimento nativo com Jetpack Compose",
                speaker = "Rafahel Mello e Samuel Tobias",
                date = Date(1629369000000),
                duration = Minutes(30),
                subjects = setOf("Jetpack", "Compose", "Android", "UI")
            ),
            Talk(
                id = "bbb",
                title = "Configurando R8 em um app Android",
                speaker = "André Theilacker",
                date = Date(1629370800000),
                duration = Minutes(30),
                subjects = emptySet()
            )
        )
    }
}