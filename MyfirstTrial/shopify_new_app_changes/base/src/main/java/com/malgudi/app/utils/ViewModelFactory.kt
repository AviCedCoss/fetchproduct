package com.malgudi.app.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.malgudi.app.addresssection.viewmodels.AddressModel
import com.malgudi.app.basesection.viewmodels.LeftMenuViewModel
import com.malgudi.app.basesection.viewmodels.SplashViewModel
import com.malgudi.app.cartsection.viewmodels.CartListViewModel
import com.malgudi.app.checkoutsection.viewmodels.CheckoutWebLinkViewModel
import com.malgudi.app.collectionsection.viewmodels.CollectionMenuViewModel
import com.malgudi.app.collectionsection.viewmodels.CollectionViewModel
import com.malgudi.app.homesection.viewmodels.HomePageViewModel
import com.malgudi.app.loginsection.viewmodels.LoginViewModel
import com.malgudi.app.loginsection.viewmodels.RegistrationViewModel
import com.malgudi.app.ordersection.viewmodels.OrderDetailsViewModel
import com.malgudi.app.ordersection.viewmodels.OrderListViewModel
import com.malgudi.app.personalised.viewmodels.PersonalisedViewModel
import com.malgudi.app.productsection.viewmodels.ProductListModel
import com.malgudi.app.productsection.viewmodels.ProductViewModel
import com.malgudi.app.repositories.Repository
import com.malgudi.app.searchsection.viewmodels.SearchListModel
import com.malgudi.app.userprofilesection.viewmodels.UserProfileViewModel
import com.malgudi.app.wishlistsection.viewmodels.WishListViewModel
import com.malgudi.app.yotporewards.earnrewards.EarnRewardsViewModel
import com.malgudi.app.yotporewards.getrewards.GetRewardsViewModel
import com.malgudi.app.yotporewards.myrewards.MyRewardsViewModel
import com.malgudi.app.yotporewards.referfriend.ReferFriendViewModel
import com.malgudi.app.yotporewards.rewarddashboard.RewardDashbordViewModel

import javax.inject.Inject

class ViewModelFactory @Inject
constructor(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(LeftMenuViewModel::class.java)) {
            return LeftMenuViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(ProductListModel::class.java)) {
            return ProductListModel(repository) as T
        }
        if (modelClass.isAssignableFrom(CollectionViewModel::class.java)) {
            return CollectionViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(CollectionMenuViewModel::class.java)) {
            return CollectionMenuViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(WishListViewModel::class.java)) {
            return WishListViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(CartListViewModel::class.java)) {
            return CartListViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(CheckoutWebLinkViewModel::class.java)) {
            return CheckoutWebLinkViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(SearchListModel::class.java)) {
            return SearchListModel(repository) as T
        }
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            return UserProfileViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(OrderListViewModel::class.java)) {
            return OrderListViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(OrderDetailsViewModel::class.java)) {
            return OrderDetailsViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(AddressModel::class.java)) {
            return AddressModel(repository) as T
        }
        if (modelClass.isAssignableFrom(HomePageViewModel::class.java)) {
            return HomePageViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(PersonalisedViewModel::class.java)) {
            return PersonalisedViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(GetRewardsViewModel::class.java)) {
            return GetRewardsViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(EarnRewardsViewModel::class.java)) {
            return EarnRewardsViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(MyRewardsViewModel::class.java)) {
            return MyRewardsViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(RewardDashbordViewModel::class.java)) {
            return RewardDashbordViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(ReferFriendViewModel::class.java)) {
            return ReferFriendViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
