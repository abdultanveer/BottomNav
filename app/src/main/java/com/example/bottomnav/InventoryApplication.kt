package com.example.bottomnav

import android.app.Application
import com.example.bottomnav.data.database.ItemRoomDatabase

class InventoryApplication: Application() {

    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }

}