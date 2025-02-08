package com.example.encartados

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.encartados.databinding.DialogAddItemBinding

class AddItemDialog(context: Context, private val dbHelper: UserDatabaseHelper, private val onItemAdded: () -> Unit) : Dialog(context) {

    private lateinit var binding: DialogAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val itemName = binding.etItemName.text.toString()
            val itemQuantity = binding.etItemQuantity.text.toString().toIntOrNull()

            if (itemName.isEmpty() || itemQuantity == null) {
                Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(UserDatabaseHelper.COLUMN_ITEM_NAME, itemName)
                put(UserDatabaseHelper.COLUMN_ITEM_QUANTITY, itemQuantity)
            }

            val newRowId = db.insert(UserDatabaseHelper.TABLE_STOCK, null, values)
            if (newRowId != -1L) {
                Toast.makeText(context, "Item added successfully", Toast.LENGTH_SHORT).show()
                onItemAdded()
                dismiss()
            } else {
                Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}