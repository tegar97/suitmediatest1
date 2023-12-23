package com.tegar.suitmediatest1.utils

object  Logic {
    fun isPalindrome(s: String): Boolean {
        var start = 0
        var end = s.length - 1

        while (start < end) {
            if (s[start] != s[end]) {
                return false
            }
            start++
            end--
        }

        return true
    }

}