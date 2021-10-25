package com.example.androidkotlin

class ListWithTrash<E>(
    private val innerList: MutableList<E> = ArrayList<E>()
): MutableList<E> by innerList {

    var deletedItem: E? = null

    override fun remove(element: E): Boolean {
        deletedItem = element
        return innerList.remove(element)
    }

    fun recover(): E?{
        return deletedItem
    }

}