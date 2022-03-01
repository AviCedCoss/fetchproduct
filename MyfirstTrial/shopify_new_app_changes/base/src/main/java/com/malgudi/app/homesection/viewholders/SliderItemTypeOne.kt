package com.malgudi.app.homesection.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.malgudi.app.databinding.MCustomisableListBinding
import com.malgudi.app.databinding.MMultiplegridBinding
import com.malgudi.app.databinding.MSlideritemoneBinding
import com.malgudi.app.databinding.MSlideritemtwoBinding

class SliderItemTypeOne : RecyclerView.ViewHolder {
    lateinit var binding: MSlideritemoneBinding
    lateinit var bindingtwo: MSlideritemtwoBinding
    lateinit var listbinding: MCustomisableListBinding
    lateinit var gridbinding: MMultiplegridBinding

    constructor(binding: MSlideritemoneBinding) : super(binding.root) {
        this.binding = binding
    }

    constructor(bindingtwo: MSlideritemtwoBinding) : super(bindingtwo.root) {
        this.bindingtwo = bindingtwo
    }
    constructor(listbinding: MCustomisableListBinding) : super(listbinding.root) {
        this.listbinding = listbinding
    }
    constructor(gridbinding: MMultiplegridBinding) : super(gridbinding.root) {
        this.gridbinding = gridbinding
    }
}
