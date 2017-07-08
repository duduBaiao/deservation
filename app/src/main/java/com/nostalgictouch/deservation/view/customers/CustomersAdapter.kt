package com.nostalgictouch.deservation.view.customers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.nostalgictouch.deservation.R
import com.nostalgictouch.deservation.model.Customer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_customer.view.*

class CustomersAdapter(val customers: List<Customer>,
                       val itemClick: (Customer) -> Unit) :
        RecyclerView.Adapter<CustomersAdapter.ViewHolder>(), Filterable {

    private var mFilteredCustomers: List<Customer>

    init {
        mFilteredCustomers = customers;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(mFilteredCustomers[position], itemClick)
    }

    override fun getItemCount() = mFilteredCustomers.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): Filter.FilterResults {
                val terms = constraint.toString().toLowerCase()

                if (terms.isEmpty()) {
                    mFilteredCustomers = customers;
                }
                else {
                    mFilteredCustomers = customers.filter { it.fullName().toLowerCase().contains(terms) }
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = mFilteredCustomers
                filterResults.count = mFilteredCustomers.size

                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                mFilteredCustomers = filterResults.values as List<Customer>
                notifyDataSetChanged()
            }
        }
    }

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
