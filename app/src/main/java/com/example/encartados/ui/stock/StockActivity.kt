package com.example.encartados.ui.stock

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.encartados.Cart
import com.example.encartados.R
import com.example.encartados.utils.adapters.StockAdapter
import com.example.encartados.utils.adapters.StockItem
import com.example.encartados.database.UserDatabaseHelper
import com.example.encartados.databinding.ActivityStockBinding

class StockActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStockBinding
    private lateinit var stockAdapter: StockAdapter
    private lateinit var dbHelper: UserDatabaseHelper
    private val cart = Cart()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = UserDatabaseHelper(this)
        stockAdapter = StockAdapter(mutableListOf(), { stockItem ->
            AddToCartDialog(this, stockItem) { quantity ->
                cart.addItem(stockItem, quantity)
            }.show()
        }, { stockItem ->
            EditItemDialog(this, stockItem, dbHelper) {
                loadStockItems()
            }.show()
        }, { stockItem ->
            deleteStockItem(stockItem)
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@StockActivity)
            adapter = stockAdapter
        }

        binding.fabAddItem.setOnClickListener {
            AddItemDialog(this, dbHelper) {
                loadStockItems()
            }.show()
        }

        binding.fabCart.setOnClickListener {
            CartDialog(this, cart) {
                Toast.makeText(this, "Purchase completed", Toast.LENGTH_SHORT).show()
            }.show()
        }

        findViewById<Button>(R.id.buttonBackToHome).setOnClickListener {
            finish()
        }

        loadStockItems()
    }

    private fun loadStockItems() {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            UserDatabaseHelper.TABLE_STOCK,
            null, null, null, null, null, null
        )

        val items = mutableListOf<StockItem>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_STOCK_ID))
                val name = getString(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ITEM_NAME))
                val quantity = getInt(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ITEM_QUANTITY))
                val price = getDouble(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ITEM_PRICE))
                val imageResId = getInt(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ITEM_IMAGE))
                items.add(StockItem(id, name, quantity, price, imageResId))
            }
        }
        cursor.close()
        stockAdapter.updateItems(items)
    }

    private fun deleteStockItem(stockItem: StockItem) {
        val db = dbHelper.writableDatabase
        db.delete(UserDatabaseHelper.TABLE_STOCK, "${UserDatabaseHelper.COLUMN_STOCK_ID} = ?", arrayOf(stockItem.id.toString()))
        loadStockItems()
    }
}