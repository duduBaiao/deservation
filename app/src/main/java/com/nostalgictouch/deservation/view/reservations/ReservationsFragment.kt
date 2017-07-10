package com.nostalgictouch.deservation.view.reservations

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nostalgictouch.deservation.R
import com.nostalgictouch.deservation.data.livedata.common.Status
import com.nostalgictouch.deservation.model.TableReservation
import com.nostalgictouch.deservation.utils.test.EspressoIdlingResource
import com.nostalgictouch.deservation.view.common.BaseFragment
import com.nostalgictouch.deservation.viewmodel.ReservationsViewModel
import kotlinx.android.synthetic.main.fragment_reservations.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton

class ReservationsFragment : BaseFragment() {

    private lateinit var viewModel: ReservationsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reservations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        val columnCount = resources.getInteger(R.integer.reservations_grid_columns_count)
        tablesRecyclerView.setHasFixedSize(true)
        tablesRecyclerView.layoutManager = GridLayoutManager(activity, columnCount)

        if (savedInstanceState == null) {
            loadData()
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

    fun updateAdapter(tableReservations: List<TableReservation>) {
        tablesRecyclerView.adapter = ReservationsAdapter(tableReservations) {
            tableReservation, position ->

            confirmStatusChange(tableReservation, position)
        }
    }

    private fun confirmStatusChange(tableReservation: TableReservation, position: Int) {
        val messageId =
                if (tableReservation.available) R.string.confirm_reservation
                else R.string.confirm_reservation_cancelation

        alert(messageId) {
            yesButton {
                toggleReservationStatus(tableReservation, position)
            }
            noButton {}
        }.show()
    }

    private fun toggleReservationStatus(tableReservation: TableReservation, position: Int) {
        EspressoIdlingResource.increment()

        viewModel.toggleReservationStatus(tableReservation)
                .subscribe {
                    tablesRecyclerView.adapter.notifyItemChanged(position)

                    if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
    }

    override fun loadData() {
        super.loadData()

        viewModel.loadReservations()
    }
}
