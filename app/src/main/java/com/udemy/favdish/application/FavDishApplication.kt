package com.udemy.favdish.application

import android.app.Application
import com.udemy.favdish.model.database.FavDishRepository
import com.udemy.favdish.model.database.FavDishRoomDatabase

class FavDishApplication : Application() {

    private val database by lazy { FavDishRoomDatabase.getDatabase(this@FavDishApplication) }

    // A variable for repository.
    val repository by lazy { FavDishRepository(database.favDishDao()) }
}