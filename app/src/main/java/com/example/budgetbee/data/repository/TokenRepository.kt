package com.example.budgetbee.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.budgetbee.data.model.Token
import com.example.budgetbee.data.model.User
import com.example.budgetbee.data.repository.UserRepository.Companion.USER_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class TokenRepository(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("token")
        val TOKEN_KEY = stringPreferencesKey("token")
    }

    val getToken: Flow<Token?> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN_KEY]?.let { json ->
                try {
                    Json.decodeFromString<Token>(json)
                } catch (e: Exception) {
                    null
                }
            }
        }

    suspend fun saveToken(token: Token) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = Json.encodeToString(token)
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}

