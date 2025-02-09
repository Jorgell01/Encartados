package com.example.encartados.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.encartados.databinding.ItemStockBinding

data class StockItem(val id: Int, val name: String, var quantity: Int, val price: Double, val imageResId: Int)

class StockAdapter(
    private var items: MutableList<StockItem>,
    private val onItemClicked: (StockItem) -> Unit,
    private val onEditItem: (StockItem) -> Unit,
    private val onDeleteItem: (StockItem) -> Unit
) : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    inner class StockViewHolder(val binding: ItemStockBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val binding = ItemStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvItemName.text = item.name
        holder.binding.tvItemStock.text = "Stock: ${item.quantity}"
        holder.binding.tvItemPrice.text = "Price: $${item.price}"
        holder.binding.ivItemImage.setImageResource(item.imageResId)

        holder.binding.root.setOnClickListener {
            onItemClicked(item)
        }

        holder.binding.btnEdit.setOnClickListener {
            onEditItem(item)
        }

        holder.binding.btnDelete.setOnClickListener {
            onDeleteItem(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<StockItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}