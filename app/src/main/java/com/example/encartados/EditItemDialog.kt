package com.example.encartados

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.encartados.databinding.DialogEditItemBinding

class EditItemDialog(context: Context, private val stockItem: StockItem, private val dbHelper: UserDatabaseHelper, private val onItemEdited: () -> Unit) : Dialog(context) {

    private lateinit var binding: DialogEditItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etItemName.setText(stockItem.name)
        binding.etItemQuantity.setText(stockItem.quantity.toString())
        binding.etItemPrice.setText(stockItem.price.toString())

        binding.btnEdit.setOnClickListener {
            val itemName = binding.etItemName.text.toString()
            val itemQuantity = binding.etItemQuantity.text.toString().toIntOrNull()
            val itemPrice = binding.etItemPrice.text.toString().toDoubleOrNull()

            if (itemName.isEmpty() || itemQuantity == null || itemPrice == null) {
                Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(UserDatabaseHelper.COLUMN_ITEM_NAME, itemName)
                put(UserDatabaseHelper.COLUMN_ITEM_QUANTITY, itemQuantity)
                put(UserDatabaseHelper.COLUMN_ITEM_PRICE, itemPrice)
            }

            db.update(UserDatabaseHelper.TABLE_STOCK, values, "${UserDatabaseHelper.COLUMN_STOCK_ID} = ?", arrayOf(stockItem.id.toString()))
            Toast.makeText(context, "Item edited successfully", Toast.LENGTH_SHORT).show()
            onItemEdited()
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}