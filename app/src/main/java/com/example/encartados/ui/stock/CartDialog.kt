package com.example.encartados.ui.stock

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.encartados.Cart
import com.example.encartados.utils.adapters.CartAdapter
import com.example.encartados.CartItem
import com.example.encartados.databinding.DialogCartBinding

class CartDialog(context: Context, private val cart: Cart, private val onPurchaseCompleted: () -> Unit) : Dialog(context) {

    private lateinit var binding: DialogCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cartItems = cart.getItems()
        val adapter = CartAdapter(cartItems, { cartItem ->
            cart.removeItem(cartItem)
            returnStock(cartItem)
            updateCartView()
        }, { cartItem ->
            returnStock(cartItem)
        })
        binding.recyclerViewCart.adapter = adapter

        updateCartView()

        binding.btnPurchase.setOnClickListener {
            cart.clear()
            onPurchaseCompleted()
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnClearCart.setOnClickListener {
            cart.getItems().forEach { returnStock(it) }
            cart.clear()
            updateCartView()
        }
    }

    private fun updateCartView() {
        val cartItems = cart.getItems()
        val totalAmount = cartItems.sumOf { it.stockItem.price * it.quantity }
        binding.tvTotalAmount.text = "Total: $$totalAmount"
        (binding.recyclerViewCart.adapter as CartAdapter).updateItems(cartItems)

        // Update the list of items in the cart
        val itemsList = cartItems.joinToString("\n") { "${it.stockItem.name}: ${it.quantity}" }
        binding.tvItemsList.text = itemsList
    }

    private fun returnStock(cartItem: CartItem) {
        cartItem.stockItem.quantity += cartItem.quantity
    }
}