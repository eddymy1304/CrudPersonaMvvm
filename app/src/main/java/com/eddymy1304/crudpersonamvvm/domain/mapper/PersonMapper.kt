package com.eddymy1304.crudpersonamvvm.domain.mapper

import com.eddymy1304.crudpersonamvvm.data.database.entity.PersonEntity
import com.eddymy1304.crudpersonamvvm.data.remote.response.PersonResponse
import com.eddymy1304.crudpersonamvvm.domain.model.PersonModel
import com.eddymy1304.crudpersonamvvm.domain.model.TypeDocument

object PersonMapper : ResponseToDomainMapper<PersonResponse, PersonModel>,
    EntityToDomainMapper<PersonEntity, PersonModel> {
    override fun toDomainFromResponse(response: PersonResponse): PersonModel {
        return PersonModel(
            numberDocument = response.numeroDocumento.orEmpty(),
            name = response.nombres.orEmpty(),
            lastName = "${response.apellidoPaterno.orEmpty()} ${response.apellidoMaterno.orEmpty()}",
            age = -1,
            typeDocument = TypeDocument
                .entries
                .find { it.value == response.tipoDocumento }
                ?: TypeDocument.UNKNOWN
        )
    }

    override fun toDomainFromEntity(entity: PersonEntity): PersonModel {
        return PersonModel(
            numberDocument = entity.numberDocument,
            name = entity.name,
            lastName = entity.lastName,
            age = entity.age,
            typeDocument = TypeDocument
                .entries
                .find { it.value == entity.typeDocument }
                ?: TypeDocument.UNKNOWN
        )
    }

    override fun toEntityFromDomain(domain: PersonModel): PersonEntity {
        return PersonEntity(
            numberDocument = domain.numberDocument,
            name = domain.name,
            lastName = domain.lastName,
            age = domain.age,
            typeDocument = domain.typeDocument.value
        )
    }
}

fun PersonResponse.toDomainFromResponse() = PersonMapper.toDomainFromResponse(this)

fun PersonEntity.toDomainFromEntity() = PersonMapper.toDomainFromEntity(this)

fun List<PersonEntity>.toDomainFromEntity() = this.map { it.toDomainFromEntity() }

fun PersonModel.toEntityFromDomain() = PersonMapper.toEntityFromDomain(this)