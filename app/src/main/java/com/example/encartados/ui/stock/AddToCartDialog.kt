package com.example.encartados.ui.stock

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.encartados.utils.adapters.StockItem
import com.example.encartados.databinding.DialogAddToCartBinding

class AddToCartDialog(context: Context, private val stockItem: StockItem, private val onItemAdded: (Int) -> Unit) : Dialog(context) {

    private lateinit var binding: DialogAddToCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddToCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvItemName.text = stockItem.name
        binding.tvItemStock.text = "Stock: ${stockItem.quantity}"

        binding.btnAddToCart.setOnClickListener {
            val quantity = binding.etQuantity.text.toString().toIntOrNull()

            if (quantity == null || quantity <= 0) {
                Toast.makeText(context, "Please enter a valid quantity", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (quantity > stockItem.quantity) {
                Toast.makeText(context, "Quantity exceeds stock", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            stockItem.quantity -= quantity
            onItemAdded(quantity)
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}