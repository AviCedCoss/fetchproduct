package com.malgudi.app.checkoutsection.activities

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.malgudi.app.MyApplication
import com.malgudi.app.R
import com.malgudi.app.basesection.activities.NewBaseActivity
import com.malgudi.app.basesection.viewmodels.SplashViewModel
import com.malgudi.app.databinding.ActivityOrderSuccessBinding
import com.malgudi.app.homesection.activities.HomePage
import com.malgudi.app.sharedprefsection.MagePrefs
import com.malgudi.app.utils.Constant

class OrderSuccessActivity : NewBaseActivity() {
    lateinit var binding: ActivityOrderSuccessBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content = findViewById<ViewGroup>(R.id.container)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_order_success, content, true)
        (application as MyApplication).mageNativeAppComponent!!.orderSuccessInjection(this)
        firebaseAnalytics = Firebase.analytics
        showTittle(resources.getString(R.string.checkout))
        showBackButton()
        if (SplashViewModel.featuresModel.firebaseEvents) {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.PURCHASE) {
                param(FirebaseAnalytics.Param.CURRENCY, MagePrefs.getCurrency() ?: "")
                param(FirebaseAnalytics.Param.PRICE, MagePrefs.getGrandTotal() ?: "")
            }
        }
        binding.continueShopping.setOnClickListener {
            startActivity(Intent(this, HomePage::class.java))
            Constant.activityTransition(this)
            finish()
        }
    }
}