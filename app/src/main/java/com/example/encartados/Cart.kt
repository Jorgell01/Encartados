package com.example.encartados

import com.example.encartados.utils.adapters.StockItem

data class CartItem(val stockItem: StockItem, var quantity: Int)

class Cart {
    private val items = mutableListOf<CartItem>()

    fun addItem(stockItem: StockItem, quantity: Int) {
        val existingItem = items.find { it.stockItem.id == stockItem.id }
        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            items.add(CartItem(stockItem, quantity))
        }
    }

    fun removeItem(cartItem: CartItem) {
        items.remove(cartItem)
    }

    fun clear() {
        items.clear()
    }

    fun getItems(): List<CartItem> = items
}