package com.example.encartados

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.encartados.databinding.ItemStockBinding

data class StockItem(val id: Int, val name: String, var quantity: Int, val price: Double)

class StockAdapter(
    private var items: MutableList<StockItem>,
    private val onItemClicked: (StockItem) -> Unit
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

        holder.itemView.setOnClickListener {
            onItemClicked(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<StockItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}