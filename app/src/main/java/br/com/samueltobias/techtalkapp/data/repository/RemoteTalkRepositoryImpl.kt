package br.com.samueltobias.techtalkapp.data.repository

import br.com.samueltobias.techtalkapp.data.api.MockApi
import br.com.samueltobias.techtalkapp.domain.model.Talk
import br.com.samueltobias.techtalkapp.domain.repository.TalkRepository

class RemoteTalkRepositoryImpl(private val api: MockApi) : TalkRepository {
    override suspend fun getTalks(): List<Talk> {
        return api.getTalks().body() ?: emptyList()
    }
}