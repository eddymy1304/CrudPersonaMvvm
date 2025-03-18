package com.eddymy1304.crudpersonamvvm.domain.mapper

interface ResponseToDomainMapper<Response, Domain> {

    fun toDomainFromResponse(response: Response): Domain

}

interface EntityToDomainMapper<Entity, Domain> {

    fun toDomainFromEntity(entity: Entity): Domain

    fun toEntityFromDomain(domain: Domain): Entity
}