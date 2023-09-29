package com.rbs.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rbs.core.domain.usecase.AgentsUseCase

class FavoriteViewModel(agentsUseCase: AgentsUseCase) : ViewModel() {

    val getFavoriteAgents = agentsUseCase.getFavoriteAgents().asLiveData()
}