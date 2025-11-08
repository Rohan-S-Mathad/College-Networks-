package com.example.myapplication2.data

data class UserProfile(
    val phoneNumber: String,
    val name: String,
    val email: String,
    val address: String,
    val branch: String = "Computer Science",
    val college: String = "University of Los Angeles",
    val classNumber: String = "CS-2024-A",
    val classTeacherName: String = "Dr. Sarah Johnson",
    val teacherContact: String = "9988776655",
    val fatherName: String = "N/A",
    val fatherContact: String = "N/A"
)
