package com.nostalgictouch.deservation.view.reservations

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nostalgictouch.deservation.R
import com.nostalgictouch.deservation.model.TableReservation
import kotlinx.android.synthetic.main.item_table.view.*

class ReservationsAdapter(val tableReservations: List<TableReservation>,
                          val itemClick: (TableReservation, Int) -> Unit) :
        RecyclerView.Adapter<ReservationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_table, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(tableReservations[position], position, itemClick)
    }

    override fun getItemCount() = tableReservations.size

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var tableText: String
        private var availableText: String
        private var reservedText: String

        private var availableBackgroundColor: Int
        private var reservedBackgroundColor: Int

        private var availableTextColor: Int
        private var reservedTextColor: Int

        init {
            tableText = itemView.context.resources.getString(R.string.table)
            availableText = itemView.context.resources.getString(R.string.available)
            reservedText = itemView.context.resources.getString(R.string.reserved)

            availableBackgroundColor = ContextCompat.getColor(itemView.context, R.color.available_table_background)
            reservedBackgroundColor = ContextCompat.getColor(itemView.context, R.color.reserved_table_background)

            availableTextColor = ContextCompat.getColor(itemView.context, R.color.available_table_status)
            reservedTextColor = ContextCompat.getColor(itemView.context, R.color.reserved_table_status)
        }

        fun bindView(tableReservation: TableReservation, position: Int, itemClick: (TableReservation, Int) -> Unit) {

            itemView.title.text = tableText + " " + tableReservation.id

            if (tableReservation.available) {
                itemView.setBackgroundColor(availableBackgroundColor)

                itemView.status.setTextColor(availableTextColor)
                itemView.status.setText(availableText)
            }
            else {
                itemView.setBackgroundColor(reservedBackgroundColor)

                itemView.status.setTextColor(reservedTextColor)
                itemView.status.setText(reservedText)
            }

            itemView.clickableLayout.setOnClickListener { itemClick(tableReservation, position) }
        }
    }
}
