package com.example.coroutinescomposeapp.data.local.db.dao

import androidx.room.*
import com.example.coroutinescomposeapp.data.local.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewUser(user: UserEntity)

    @Query("SELECT * FROM user WHERE email = :userMail")
    fun getUserByEmail(userMail: String): UserEntity

    @Query("DELETE FROM user WHERE email =:userMail")
    fun deleteUserByEmail(userMail: String)
}