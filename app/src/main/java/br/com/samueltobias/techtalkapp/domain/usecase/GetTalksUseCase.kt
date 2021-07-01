package br.com.samueltobias.techtalkapp.domain.usecase

import br.com.samueltobias.techtalkapp.domain.model.Talk
import br.com.samueltobias.techtalkapp.domain.repository.TalkRepository

interface GetTalksUseCase {
    suspend fun getTalks() : List<Talk>
}

class GetTalksUseCaseImpl(private val repository: TalkRepository) : GetTalksUseCase {
    override suspend fun getTalks(): List<Talk> {
        return repository.getTalks()
    }
}