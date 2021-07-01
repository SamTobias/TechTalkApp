package br.com.samueltobias.techtalkapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samueltobias.techtalkapp.data.api.MockRetrofitClientApi
import br.com.samueltobias.techtalkapp.data.repository.TalkRepositoryImpl
import br.com.samueltobias.techtalkapp.domain.usecase.GetTalksUseCase
import br.com.samueltobias.techtalkapp.domain.usecase.GetTalksUseCaseImpl
import br.com.samueltobias.techtalkapp.ui.model.Talk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    val talks = MutableLiveData<List<Talk>>()
    private val useCase: GetTalksUseCase = GetTalksUseCaseImpl(TalkRepositoryImpl(MockRetrofitClientApi.getApi()))

    fun getTalks() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mutableListOf<Talk>()
            for (talk in useCase.getTalks()) {
                result.add(talk.toUiModel())
            }
            talks.postValue(result)
        }
    }
}