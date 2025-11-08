package com.example.myapplication2.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object UserRepository {
    private var userDatabase: Map<String, UserProfile>? = null

    // Fallback data if assets file is not available
    private val defaultUserDatabase = mapOf(
        "1234567890" to UserProfile(
            phoneNumber = "1234567890",
            name = "John Doe",
            email = "john.doe@example.com",
            address = "123 Main Street, City, Country",
            branch = "Computer Science Engineering",
            college = "University of Los Angeles",
            classNumber = "CSE-2024-A",
            classTeacherName = "Dr. Sarah Johnson",
            teacherContact = "9988776655",
            fatherName = "Robert Doe",
            fatherContact = "9876543210"
        ),
        "9876543210" to UserProfile(
            phoneNumber = "9876543210",
            name = "Jane Smith",
            email = "jane.smith@example.com",
            address = "456 Oak Avenue, Town, Country",
            branch = "Electronics and Communication",
            college = "University of Los Angeles",
            classNumber = "ECE-2024-B",
            classTeacherName = "Prof. Michael Chen",
            teacherContact = "9988112233",
            fatherName = "William Smith",
            fatherContact = "9123456789"
        )
    )

    private fun loadUsersFromAssets(context: Context): Map<String, UserProfile> {
        return try {
            val json = context.assets.open("users.json").bufferedReader().use { it.readText() }
            val gson = Gson()
            val listType = object : TypeToken<List<UserProfile>>() {}.type
            val userList: List<UserProfile> = gson.fromJson(json, listType)
            userList.associateBy { it.phoneNumber }
        } catch (e: Exception) {
            e.printStackTrace()
            defaultUserDatabase
        }
    }

    private fun initializeDatabase(context: Context) {
        if (userDatabase == null) {
            userDatabase = loadUsersFromAssets(context)
        }
    }

    fun getUserByPhoneNumber(context: Context, phoneNumber: String): UserProfile? {
        initializeDatabase(context)
        return userDatabase?.get(phoneNumber)
    }

    // Keep old method for backward compatibility but mark as deprecated
    @Deprecated("Use getUserByPhoneNumber(context, phoneNumber) instead")
    fun getUserByPhoneNumber(phoneNumber: String): UserProfile? {
        return defaultUserDatabase[phoneNumber]
    }

    fun saveCurrentProfile(context: Context, profile: UserProfile) {
        val file = File(context.filesDir, "current_profile")
        val gson = Gson()
        val json = gson.toJson(profile)
        file.writeText(json)

        // Mark user as logged in
        setUserLoggedIn(context, true)
    }

    fun getCurrentProfile(context: Context): UserProfile? {
        val file = File(context.filesDir, "current_profile")
        if (!file.exists()) return null

        val json = file.readText()
        val gson = Gson()
        return gson.fromJson(json, UserProfile::class.java)
    }

    // Session management methods
    fun isUserLoggedIn(context: Context): Boolean {
        val file = File(context.filesDir, "session_state")
        if (!file.exists()) return false
        return file.readText().toBoolean()
    }

    fun setUserLoggedIn(context: Context, isLoggedIn: Boolean) {
        val file = File(context.filesDir, "session_state")
        file.writeText(isLoggedIn.toString())
    }

    fun signOut(context: Context) {
        // Mark user as signed out
        setUserLoggedIn(context, false)

        // Optionally delete profile data
        val profileFile = File(context.filesDir, "current_profile")
        if (profileFile.exists()) {
            profileFile.delete()
        }
    }
}
