package ru.skillbranch.kotlinexample

import androidx.annotation.VisibleForTesting

object UserHolder {
    private val map = mutableMapOf<String, User>()

    fun registerUser(fullName: String, email: String, password: String): User {
        if (map.containsKey(email.toLowerCase())) {
            throw IllegalArgumentException("A user with this email already exists")
        }

        val user = User.makeUser(fullName = fullName, email = email, password = password)
        map[user.login] = user
        return user
    }

    fun registerUserByPhone(fullName: String, rowPhone: String): User {
        if (!rowPhone.matches(regex = "^\\+[0-9]\\s?\\([0-9]{3}\\)\\s?[0-9]{3}\\s?-?[0-9]{2}-[0-9]{2}".toRegex())) {
            throw IllegalArgumentException("Enter a valid phone number starting with a + and containing 11 digits")
        }

        if (map.containsKey(rowPhone)) {
            throw IllegalArgumentException("A user with this phone already exists")
        }

        val makeUser = User.makeUser(fullName = fullName, phone = rowPhone)
        map[rowPhone] = makeUser
        return makeUser
    }

    fun loginUser(login: String, password: String): String? =
        map[login.trim()]?.let {
            if (it.checkPassword(password)) it.userInfo
            else null
        }

    fun requestAccessCode(login: String): Unit {
        map[login]?.let {
            val accessCode = it.generateAccessCode()
            it.accessCode?.let { x -> it.changePassword(x, accessCode) }
            map.put(login, it)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder() {
        map.clear()
    }
}