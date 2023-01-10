package com.hishamreffat.shoestore.screens.shoes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hishamreffat.shoestore.R
import com.hishamreffat.shoestore.databinding.FragmentShoesListBinding
import com.hishamreffat.shoestore.models.ShoesListViewModel

class ShoesListFragment : Fragment() {
    private lateinit var viewModel: ShoesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoesListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)
        viewModel = ViewModelProvider(this)[ShoesListViewModel::class.java]
        //val shoeText:TextView =TextView(this)
       // binding.shoeLinearLayout.addView(viewModel.shoeList)
        // Inflate the layout for this fragment
        return binding.root
    }
}