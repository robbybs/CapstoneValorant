package com.rbs.core.domain.repository

import com.rbs.core.data.Resource
import com.rbs.core.domain.model.Agents
import kotlinx.coroutines.flow.Flow

interface IAgentsRepository {

    fun getAllAgents(): Flow<Resource<List<Agents>>>

    fun getFavoriteAgents(): Flow<List<Agents>>

    fun setFavoriteAgents(agents: Agents, state: Boolean)
}