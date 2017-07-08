package com.nostalgictouch.deservation.view.reservations

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nostalgictouch.deservation.R
import com.nostalgictouch.deservation.data.livedata.common.Status
import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.model.Table
import com.nostalgictouch.deservation.view.common.BaseFragment
import com.nostalgictouch.deservation.viewmodel.ReservationsViewModel
import kotlinx.android.synthetic.main.fragment_reservations.*

class ReservationsFragment : BaseFragment() {

    private lateinit var viewModel: ReservationsViewModel

    lateinit private var mCustomer: Customer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reservations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCustomer = activity.intent.extras.getParcelable("customer")

        setupViewModel()

        val columnCount = resources.getInteger(R.integer.reservations_grid_columns_count)
        tablesRecyclerView.setHasFixedSize(true)
        tablesRecyclerView.layoutManager = GridLayoutManager(activity, columnCount)

        if (savedInstanceState == null) {
            Handler().postDelayed({
                loadReservations()
            }, 1)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this.activity).get(ReservationsViewModel::class.java)

        viewModel.loadingStatus.observe(this.activity as LifecycleOwner, Observer {
            status ->

            if (status == Status.LOADED) tablesRecyclerView.visibility = View.VISIBLE
            else if (status == Status.ERROR) tablesRecyclerView.visibility = View.GONE

            updateBaseLayouts(status!!)
        })

        viewModel.reservations.observe(activity as LifecycleOwner, Observer {
            updateAdapter(it!!)
        })
    }

    fun updateAdapter(tables: List<Table>) {
        tablesRecyclerView.adapter = ReservationsAdapter(tables) {
            table, position ->

            table.available = !table.available
            tablesRecyclerView.adapter.notifyItemChanged(position)
        }
    }

    private fun loadReservations() {
        viewModel.loadReservations()
    }
}
