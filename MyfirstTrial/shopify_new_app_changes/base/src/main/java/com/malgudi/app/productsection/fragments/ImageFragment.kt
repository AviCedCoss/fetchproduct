package com.malgudi.app.productsection.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.gson.GsonBuilder
import com.shopify.buy3.Storefront

import com.malgudi.app.R
import com.malgudi.app.databinding.MImagefragmentBinding
import com.malgudi.app.basesection.fragments.BaseFragment
import com.malgudi.app.basesection.models.CommanModel
import com.malgudi.app.productsection.activities.VideoPlayerActivity
import com.malgudi.app.productsection.activities.ZoomActivity
import com.malgudi.app.productsection.models.MediaModel
import com.malgudi.app.utils.Constant

import java.util.Objects

class ImageFragment : BaseFragment(), View.OnClickListener {
    private var linkType: String? = null
    private var videoLink: String? = null
    private var mediaList: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<MImagefragmentBinding>(layoutInflater, R.layout.m_imagefragment, null, false)
        val mediaModel = Objects.requireNonNull<Bundle>(arguments).getSerializable("mediaModel") as MediaModel
        mediaList = Objects.requireNonNull<Bundle>(arguments).getString("mediaList")
        val url = mediaModel.previewUrl
        linkType = mediaModel.typeName
        videoLink = mediaModel.mediaUrl
        if (linkType.equals("Video") || linkType.equals("ExternalVideo")) {
            binding.playButton.visibility = View.VISIBLE
        } else {
            binding.playButton.visibility = View.GONE
        }
        val model = CommanModel()
        model.imageurl = url!!
        binding.commondata = model
        binding.image.setOnClickListener(this)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onClick(v: View?) {
        if (linkType.equals("Video") || linkType.equals("ExternalVideo")) {
            if (videoLink?.contains("youtu")!!) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(videoLink)))
                Constant.activityTransition(v?.context!!)
            } else {
                var intent = Intent(context, VideoPlayerActivity::class.java)
                intent.putExtra("videoLink", videoLink)
                context?.startActivity(intent)
                Constant.activityTransition(v?.context!!)
            }
        } else {
            var intent = Intent(context, ZoomActivity::class.java)
            intent.putExtra("images", mediaList)
            context?.startActivity(intent)
            Constant.activityTransition(v?.context!!)
        }
    }
}