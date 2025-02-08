package com.example.encartados

import android.content.ContentValues
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.encartados.databinding.ActivityStockBinding

class StockActivity : BaseActivity() {

    private lateinit var binding: ActivityStockBinding
    private lateinit var dbHelper: UserDatabaseHelper
    private lateinit var stockAdapter: StockAdapter
    private val cart = Cart()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = UserDatabaseHelper(this)
        stockAdapter = StockAdapter(mutableListOf()) { stockItem ->
            showAddToCartDialog(stockItem)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@StockActivity)
            adapter = stockAdapter
        }

        loadStockItems()

        binding.fabCart.setOnClickListener {
            showCartDialog()
        }
    }

    private fun loadStockItems() {
        val db = dbHelper.writableDatabase

        // Check if the stock table is empty
        val cursor = db.query(
            UserDatabaseHelper.TABLE_STOCK,
            arrayOf(UserDatabaseHelper.COLUMN_STOCK_ID),
            null, null, null, null, null
        )

        if (cursor.count == 0) {
            // Insert sample products
            val sampleItems = listOf(
                StockItem(0, "Product 1", 10, 9.99),
                StockItem(0, "Product 2", 5, 19.99),
                StockItem(0, "Product 3", 20, 4.99)
            )

            for (item in sampleItems) {
                val values = ContentValues().apply {
                    put(UserDatabaseHelper.COLUMN_ITEM_NAME, item.name)
                    put(UserDatabaseHelper.COLUMN_ITEM_QUANTITY, item.quantity)
                    put(UserDatabaseHelper.COLUMN_ITEM_PRICE, item.price)
                }
                db.insert(UserDatabaseHelper.TABLE_STOCK, null, values)
            }
        }
        cursor.close()

        // Load stock items from the database
        val cursor2 = db.query(
            UserDatabaseHelper.TABLE_STOCK,
            arrayOf(UserDatabaseHelper.COLUMN_STOCK_ID, UserDatabaseHelper.COLUMN_ITEM_NAME, UserDatabaseHelper.COLUMN_ITEM_QUANTITY, UserDatabaseHelper.COLUMN_ITEM_PRICE),
            null, null, null, null, null
        )

        val items = mutableListOf<StockItem>()
        while (cursor2.moveToNext()) {
            val id = cursor2.getInt(cursor2.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_STOCK_ID))
            val name = cursor2.getString(cursor2.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ITEM_NAME))
            val quantity = cursor2.getInt(cursor2.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ITEM_QUANTITY))
            val price = cursor2.getDouble(cursor2.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ITEM_PRICE))
            items.add(StockItem(id, name, quantity, price))
        }
        cursor2.close()

        stockAdapter.updateItems(items)
    }

    private fun showAddToCartDialog(stockItem: StockItem) {
        val dialog = AddToCartDialog(this, stockItem) { quantity ->
            cart.addItem(stockItem, quantity)
        }
        dialog.show()
    }

    private fun showCartDialog() {
        val dialog = CartDialog(this, cart) {
            // Handle purchase completed
        }
        dialog.show()
    }
}