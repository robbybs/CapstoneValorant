package com.rbs.capstonevalorant.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rbs.core.domain.usecase.AgentsUseCase

class MainViewModel(agentsUseCase: AgentsUseCase) : ViewModel() {

    val getAgents = agentsUseCase.getAllAgents().asLiveData()
}