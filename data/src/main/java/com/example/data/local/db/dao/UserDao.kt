package com.example.data.local.db.dao

import androidx.room.*
import com.example.data.local.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewUser(user: UserEntity)

    @Query("SELECT * FROM user WHERE email = :userMail")
    suspend fun getUserByEmail(userMail: String): UserEntity

    @Query("SELECT EXISTS (SELECT 1 FROM user WHERE email = :userMail)")
    suspend fun checkIfUserExistsByEmail(userMail: String): Boolean

    @Query("DELETE FROM user WHERE email =:userMail")
    suspend fun deleteUserByEmail(userMail: String)
}