package com.eddymy1304.crudpersonamvvm.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PersonEntity(
    @PrimaryKey val numberDocument: String,
    val name: String,
    val lastName: String,
    val age: Int,
    val typeDocument: String
)