package br.com.samueltobias.techtalkapp.data.api

import br.com.samueltobias.techtalkapp.domain.model.Talk
import retrofit2.Response
import retrofit2.http.GET

interface MockApi {

    @GET("talks")
    suspend fun getTalks(): Response<List<Talk>>
}