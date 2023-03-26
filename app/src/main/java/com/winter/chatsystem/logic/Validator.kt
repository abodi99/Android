package com.winter.chatsystem.logic

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("[A-Za-z\\d._%+-]+@[A-Za-z\\d.-]+\\.[A-Z|a-z]{2,}")
    return emailRegex.matches(email)
}

fun isValidPassword(password: String): Boolean {
    //The password must contain at least one letter (uppercase or lowercase).
    //The password must contain at least one digit (0-9).
    //The password must be at least 8 characters long.

    //val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    //return passwordRegex.matches(password)

    val uppercaseRegex = Regex(".*[A-Z].*")
    val lowercaseRegex = Regex(".*[a-z].*")
    val digitRegex = Regex(".*\\d.*")
    val lengthRegex = Regex(".{8,}")

    return password.matches(uppercaseRegex) &&
            password.matches(lowercaseRegex) &&
            password.matches(digitRegex) &&
            password.matches(lengthRegex)
}





