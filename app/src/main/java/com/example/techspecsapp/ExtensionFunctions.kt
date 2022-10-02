package com.example.techspecsapp

fun String.isValidUsername(): Boolean {
    return this.matches(Regex("[a-zA-Z]+[a-zA-Z\\d]*"))
}

fun String.isValidPassword():Boolean{
    return this.length>=4
}