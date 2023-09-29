package com.rbs.core.data.local

import com.rbs.core.data.local.entity.AgentsEntity
import com.rbs.core.data.local.room.AgentsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val agentsDao: AgentsDao) {
    fun getAllAgents(): Flow<List<AgentsEntity>> = agentsDao.getAgentsList()

    fun getFavoriteAgents(): Flow<List<AgentsEntity>> = agentsDao.getFavoriteAgents()

    suspend fun insertAgents(agentsList: List<AgentsEntity>) = agentsDao.insertAgents(agentsList)

    fun setFavoriteAgents(agentsEntity: AgentsEntity, newState: Boolean) {
        agentsEntity.favorite = newState
        agentsDao.updateFavoriteAgents(agentsEntity)
    }
}