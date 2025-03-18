package com.eddymy1304.crudpersonamvvm.domain.model

data class PersonModel(
    val numberDocument: String = "",
    val name: String = "",
    val lastName: String = "",
    val age: Int = 0,
    val typeDocument: TypeDocument = TypeDocument.DNI
)
