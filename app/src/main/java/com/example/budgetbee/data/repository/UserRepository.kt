package com.example.budgetbee.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.budgetbee.data.model.User
import com.example.budgetbee.data.remote.RetrofitClient
import com.example.budgetbee.data.response.AuthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class UserRepository(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user")
        val USER_KEY = stringPreferencesKey("user")
    }

    val getUser: Flow<User?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_KEY]?.let { json ->
                try {
                    Json.decodeFromString<User>(json)
                } catch (e: Exception) {
                    null
                }
            }
        }

    suspend fun saveUser(user: User) {
        context.dataStore.edit { preferences ->
            preferences[USER_KEY] = Json.encodeToString(user)
        }
    }

    suspend fun clearUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_KEY)
        }
    }

    suspend fun getUserRemote(userId: String, token: String) = RetrofitClient.apiService.getUser(userId, "Bearer $token")
}

