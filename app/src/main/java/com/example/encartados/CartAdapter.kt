package com.example.encartados

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.encartados.databinding.ItemCartBinding

class CartAdapter(
    private var items: List<CartItem>,
    private val onRemoveItem: (CartItem) -> Unit,
    private val onReturnStock: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvItemName.text = item.stockItem.name
        holder.binding.tvItemQuantity.text = item.quantity.toString()
        holder.binding.tvItemPrice.text = "$${item.stockItem.price * item.quantity}"

        holder.binding.btnRemove.setOnClickListener {
            onReturnStock(item)
            onRemoveItem(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<CartItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}