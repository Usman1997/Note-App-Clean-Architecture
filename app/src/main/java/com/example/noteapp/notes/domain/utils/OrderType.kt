package com.example.noteapp.notes.domain.utils

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()

}
