package com.example.binders.utils

import java.lang.ClassCastException

/**
 * Try to cast erased types and execute code if its successful
 * @return If the cast was successful
 * */
inline fun <reified T> Any?.tryCast(attempt: T.() -> Unit) : Boolean{
    return try {
        if (this is T) {
            attempt()
        }
        true
    } catch (ex: ClassCastException) {
        false
    }
}

inline fun <reified T> Any?.tryCastToClass(attempt: (Class<*>) -> Unit) : Boolean{
    return try {
        if (this as? Class<*> == T::class.java) {
            attempt(T::class.java)
        }
        true
    } catch (ex: ClassCastException) {
        false
    }
}