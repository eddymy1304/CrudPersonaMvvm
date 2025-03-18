package com.eddymy1304.crudpersonamvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eddymy1304.crudpersonamvvm.data.database.dao.PersonDao
import com.eddymy1304.crudpersonamvvm.data.database.entity.PersonEntity

@Database(entities = [PersonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

}