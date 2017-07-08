package com.nostalgictouch.whatshot.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nostalgictouch.deservation.R
import com.nostalgictouch.deservation.model.Customer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_customer.view.*

class CustomersAdapter(var customers: List<Customer>,
                       val itemClick: (Customer) -> Unit) : RecyclerView.Adapter<CustomersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(customers[position], itemClick)
    }

    override fun getItemCount() = customers.size

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(customer: Customer, itemClick: (Customer) -> Unit) {
            loadImage(customer.id)

            itemView.fullName.text = customer.fullName()
            itemView.setOnClickListener { itemClick(customer) }
        }

        private fun loadImage(customerId: Int) {
            val url = "file:///android_asset/customer_$customerId.jpg";

            Picasso.with(itemView.context)
                    .load(url)
                    .into(itemView.artworkImage)
        }
    }
}
