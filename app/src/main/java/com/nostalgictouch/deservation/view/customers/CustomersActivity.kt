package com.nostalgictouch.deservation.view.customers

import android.os.Bundle
import com.nostalgictouch.deservation.R
import com.nostalgictouch.deservation.view.common.BaseActivity

class CustomersActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_customers)

        title = resources.getString(R.string.pick_a_customer)
    }
}
