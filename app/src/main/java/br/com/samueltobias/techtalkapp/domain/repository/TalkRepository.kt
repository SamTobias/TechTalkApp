package br.com.samueltobias.techtalkapp.domain.repository

import br.com.samueltobias.techtalkapp.domain.model.Talk

interface TalkRepository {
    suspend fun getTalks() : List<Talk>
}