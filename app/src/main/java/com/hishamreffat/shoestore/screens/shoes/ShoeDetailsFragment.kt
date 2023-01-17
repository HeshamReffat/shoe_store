package com.hishamreffat.shoestore.screens.shoes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hishamreffat.shoestore.R
import com.hishamreffat.shoestore.database.shoes.ShoeItemDatabase
import com.hishamreffat.shoestore.databinding.FragmentShoeDetailsBinding
import com.hishamreffat.shoestore.models.shoes.ShoeViewModel
import com.hishamreffat.shoestore.models.shoes.ShoeViewModelFactory

class ShoeDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bindind: FragmentShoeDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = ShoeItemDatabase.getInstance(application).shoeItemDao

        val viewModelFactory = ShoeViewModelFactory(dataSource, application)

        val viewModel = ViewModelProvider(this, viewModelFactory)[ShoeViewModel::class.java]
        bindind.saveButton.setOnClickListener {
            if (bindind.shoeNameTextFiled.text!!.isEmpty()) {
                bindind.shoeNameTextFiled.error = "Name must not be Empty"
            } else if (bindind.shoeCompanyNameTextFiled.text!!.isEmpty()) {
                bindind.shoeCompanyNameTextFiled.error = "Company must not be Empty"
            } else if (viewModel.shoeSize.value == 0) {
                bindind.shoeSizeTextFiled.error = "Size must be grater than Zero"
            } else if (bindind.shoeDescriptionTextFiled.text!!.isEmpty()) {
                bindind.shoeDescriptionTextFiled.error = "Description must not be Empty"
            } else {
                viewModel.insertShoe()
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "New Shoe Added",
                    Snackbar.LENGTH_SHORT
                ).show()
                it?.findNavController()
                    ?.navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoesListFragment())
            }
        }
        bindind.cancelButton.setOnClickListener {
            it?.findNavController()
                ?.navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoesListFragment())
        }
        bindind.lifecycleOwner = this
        bindind.shoeViewModel = viewModel
        return bindind.root
    }
}