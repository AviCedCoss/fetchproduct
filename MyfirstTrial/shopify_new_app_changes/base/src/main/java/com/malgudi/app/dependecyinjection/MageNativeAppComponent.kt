package com.malgudi.app.dependecyinjection

import com.malgudi.app.addresssection.activities.AddressList
import com.malgudi.app.basesection.activities.NewBaseActivity
import com.malgudi.app.basesection.activities.Splash
import com.malgudi.app.basesection.fragments.LeftMenu
import com.malgudi.app.cartsection.activities.CartList
import com.malgudi.app.checkoutsection.activities.CheckoutWeblink
import com.malgudi.app.checkoutsection.activities.OrderSuccessActivity
import com.malgudi.app.collectionsection.activities.CollectionList
import com.malgudi.app.collectionsection.activities.CollectionListMenu
import com.malgudi.app.homesection.activities.HomePage
import com.malgudi.app.homesection.viewmodels.HomePageViewModel
import com.malgudi.app.jobservicessection.JobScheduler
import com.malgudi.app.productsection.activities.JudgeMeCreateReview
import com.malgudi.app.loginsection.activity.LoginActivity
import com.malgudi.app.loginsection.activity.RegistrationActivity
import com.malgudi.app.ordersection.activities.OrderDetails
import com.malgudi.app.ordersection.activities.OrderList
import com.malgudi.app.productsection.activities.*
import com.malgudi.app.quickadd_section.activities.QuickAddActivity
import com.malgudi.app.searchsection.activities.AutoSearch
import com.malgudi.app.userprofilesection.activities.UserProfile
import com.malgudi.app.utils.Urls
import com.malgudi.app.wishlistsection.activities.WishList
import com.malgudi.app.yotporewards.earnrewards.EarnRewardsActivity
import com.malgudi.app.yotporewards.earnrewards.FaqsActivity
import com.malgudi.app.yotporewards.getrewards.GetRewardsActivity
import com.malgudi.app.yotporewards.myrewards.MyRewardsActivity
import com.malgudi.app.yotporewards.referfriend.ReferFriendActivity
import com.malgudi.app.yotporewards.rewarddashboard.RewardDashboard
import com.malgudi.app.yotporewards.withoutlogin.RewardsPointActivity

import javax.inject.Singleton

import dagger.Component

@Component(modules = [UtilsModule::class])
@Singleton
interface MageNativeAppComponent {

    fun doSplashInjection(splash: Splash)
    fun doFilterInjection(product: FilterActivity)
    fun doProductListInjection(product: ProductList)
    fun doCollectionInjection(collectionList: CollectionList)
    fun doCollectionInjection(collectionList: CollectionListMenu)
    fun doProductViewInjection(product: ProductView)
    fun doJudgeMeReviewInjection(judgeMeCreateReview: JudgeMeCreateReview)
    fun doYotpoReviewInjection(WriteAReview: WriteAReview)
    fun doReviewListInjection(reviewListActivity: AllReviewListActivity)
    fun doAllJudgeMeReviewListInjection(judgeMeReviews: AllJudgeMeReviews)
    fun doAllAliReviewListInjection(aliReviews: AllAliReviewsListActivity)
    fun doZoomActivityInjection(base: ZoomActivity)
    fun doBaseActivityInjection(base: NewBaseActivity)
    fun doWishListActivityInjection(wish: WishList)
    fun doCartListActivityInjection(cart: CartList)
    fun doCheckoutWeblinkActivityInjection(cart: CheckoutWeblink)
    fun doAutoSearchActivityInjection(cart: AutoSearch)
    fun doLoginActivtyInjection(loginActivity: LoginActivity)
    fun doRegistrationActivityInjection(registrationActivity: RegistrationActivity)
    fun doLeftMeuInjection(left: LeftMenu)
    fun doUserProfileInjection(profile: UserProfile)
    fun doOrderListInjection(profile: OrderList)
    fun doOrderDetailsInjection(orderDetails: OrderDetails)
    fun doAddressListInjection(addressList: AddressList)
    fun doHomePageInjection(home: HomePage)
    fun doHomePageModelInjection(home: HomePageViewModel)
    fun orderSuccessInjection(orderSuccessActivity: OrderSuccessActivity)
    fun quickAddInjection(quickAddActivity: QuickAddActivity)
    fun doServiceInjection(job: JobScheduler)
    fun doURlInjection(urls: Urls)
    fun doRewarsPointsInjection(rewardsPointActivity: RewardsPointActivity)
    fun doRewarsDashbordInjection(rewardsDashboard: RewardDashboard)
    fun doGetRewadsInjection(getRewardsActivity: GetRewardsActivity)
    fun doEarnRewadsInjection(earnRewardsActivity: EarnRewardsActivity)
    fun doReferFriendInjection(referFriendActivity: ReferFriendActivity)
    fun doMyRewardInjection(myRewardsActivity: MyRewardsActivity)
    fun doFaqsInjection(faqsActivity: FaqsActivity)
}
