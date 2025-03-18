package com.eddymy1304.crudpersonamvvm.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PersonResponse(
    var apellidoMaterno: String? = null,
    var apellidoPaterno: String? = null,
    var digitoVerificador: String? = null,
    var nombres: String? = null,
    var numeroDocumento: String? = null,
    var tipoDocumento: String? = null
)