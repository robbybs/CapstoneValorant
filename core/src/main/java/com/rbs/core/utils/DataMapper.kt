package com.rbs.core.utils

import com.rbs.core.data.local.entity.AgentsEntity
import com.rbs.core.data.remote.response.AgentsResponse
import com.rbs.core.domain.model.Agents

object DataMapper {
    fun mapResponsesToEntities(input: List<AgentsResponse>): List<AgentsEntity> {
        val agentsList = ArrayList<AgentsEntity>()
        input.map {
            val agents = AgentsEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                image = it.image,
                favorite = false
            )
            agentsList.add(agents)
        }
        return agentsList
    }

    fun mapEntitiesToDomain(input: List<AgentsEntity>): List<Agents> =
        input.map {
            Agents(
                id = it.id,
                name = it.name,
                description = it.description,
                image = it.image,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntity(input: Agents) = AgentsEntity(
        id = input.id,
        name = input.name,
        description = input.description,
        image = input.image,
        favorite = input.favorite
    )
}