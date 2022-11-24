package com.hammad.jivaassisment.view.fragment.bottomFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hammad.jivaassisment.databinding.FragmentVillagesBinding
import com.hammad.jivaassisment.view.adapter.VillageSelectionAdapter

class VillageBottomSheet constructor(private val config: Config? = null) :
    BottomSheetDialogFragment(), VillageSelectionAdapter.OnVillageSelectListener {
    constructor() : this(null)

    private lateinit var binding: FragmentVillagesBinding
    private lateinit var adapter: VillageSelectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = VillageSelectionAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVillagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = adapter

        adapter.submitList(config?.list)
    }


    companion object {
        fun open(fragmentManager: FragmentManager, list: List<String>, callback: VillageBottomSheet.OnVillageSelectListener?) {
            VillageBottomSheet(Config(list, callback)).also {
                it.show(fragmentManager, null)
            }
        }
    }

    data class Config(
        val list: List<String> = emptyList(),
        @Transient val callback: VillageBottomSheet.OnVillageSelectListener? = null
    )

    fun interface OnVillageSelectListener {
        fun onVillageSelect(village: String)
    }

    override fun onVillageSelect(village: String, position: Int) {
        config?.callback?.onVillageSelect(village)
        this.dismiss()
    }
}