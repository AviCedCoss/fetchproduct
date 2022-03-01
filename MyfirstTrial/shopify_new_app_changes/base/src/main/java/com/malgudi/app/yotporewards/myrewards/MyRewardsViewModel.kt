package com.malgudi.app.yotporewards.myrewards

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.malgudi.app.network_transaction.CustomResponse
import com.malgudi.app.network_transaction.doRetrofitCall
import com.malgudi.app.repositories.Repository
import com.malgudi.app.sharedprefsection.MagePrefs
import com.malgudi.app.utils.ApiResponse
import com.malgudi.app.utils.Urls
import com.google.gson.JsonElement
import io.reactivex.disposables.CompositeDisposable

class MyRewardsViewModel(private val repository: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()
    lateinit var context: Context
    var myrewards = MutableLiveData<ApiResponse>()

    fun getMyRewards() {
        doRetrofitCall(repository.myrewards(Urls.XGUID, Urls.X_API_KEY, MagePrefs.getCustomerEmail()
                ?: "", MagePrefs.getCustomerID() ?: ""), disposables, object : CustomResponse {
            override fun onSuccessRetrofit(result: JsonElement) {
                myrewards.value = ApiResponse.success(result)
            }

            override fun onErrorRetrofit(error: Throwable) {
                myrewards.value = ApiResponse.error(error)
            }
        }, context = context)
    }
}