package com.nostalgictouch.deservation.view.reservations

import android.os.Bundle
import android.view.MenuItem
import com.nostalgictouch.deservation.R
import com.nostalgictouch.deservation.view.common.BaseActivity

class ReservationsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reservations)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
