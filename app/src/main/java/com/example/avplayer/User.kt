package com.example.avplayer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(val name:String, @PrimaryKey val id:Int)