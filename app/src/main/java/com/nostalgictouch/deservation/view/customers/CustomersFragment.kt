package com.nostalgictouch.deservation.view.customers

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nostalgictouch.deservation.R
import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.view.common.BaseFragment
import com.nostalgictouch.deservation.viewmodel.CustomersViewModel
import com.nostalgictouch.whatshot.ui.adapters.CustomersAdapter
import com.nostalgictouch.whatshot.viewmodel.trends.Status
import kotlinx.android.synthetic.main.fragment_customers.*

class CustomersFragment : BaseFragment() {

    private lateinit var viewModel: CustomersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        customersRecyclerView.layoutManager = LinearLayoutManager(activity)

        if (savedInstanceState == null) {
            loadCustomers()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this.activity).get(CustomersViewModel::class.java)

        viewModel.loadingStatus.observe(this.activity as LifecycleOwner, Observer {
            status ->

            if (status == Status.LOADED) customersRecyclerView.visibility = View.VISIBLE
            else if (status == Status.ERROR) customersRecyclerView.visibility = View.GONE

            updateBaseLayouts(status!!)
        })

        viewModel.customers.observe(activity as LifecycleOwner, Observer {
            updateAdapter(it!!)
        })
    }

    fun updateAdapter(customers: List<Customer>) {
        customersRecyclerView.adapter = CustomersAdapter(customers) {
        }
    }

    private fun loadCustomers() {
        viewModel.loadCustomers()
    }
}
