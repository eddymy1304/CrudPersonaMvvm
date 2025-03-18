package com.eddymy1304.crudpersonamvvm.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eddymy1304.crudpersonamvvm.data.database.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(entity = PersonEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(person: PersonEntity)

    @Update(entity = PersonEntity::class)
    suspend fun update(person: PersonEntity)

    @Delete(entity = PersonEntity::class)
    suspend fun delete(person: PersonEntity)

    @Query("select * from person")
    fun getAll(): Flow<List<PersonEntity>>

    @Query("select * from person where numberDocument = :numberDocument")
    fun getByNumberDocument(numberDocument: String): Flow<PersonEntity?>

}