package com.hishamreffat.shoestore.screens.shoes

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.hishamreffat.shoestore.R
import com.hishamreffat.shoestore.database.ShoeItemDatabase
import com.hishamreffat.shoestore.databinding.FragmentShoesListBinding
import com.hishamreffat.shoestore.models.ShoeViewModel
import com.hishamreffat.shoestore.models.ShoeViewModelFactory

class ShoesListFragment : Fragment() {
    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoesListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)
        val application = requireNotNull(this.activity).application

        val dataSource = ShoeItemDatabase.getInstance(application).shoeItemDao

        val viewModelFactory = ShoeViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, viewModelFactory)[ShoeViewModel::class.java]
//viewModel.shoes.observe(viewLifecycleOwner, Observer { shoe->
//    shoe.forEach {
//        Log.i("sasa", it?.shoeName ?: "notFound")
//      //  binding.shoeText.text = it?.shoeName ?:"notFound"
//    }
//})
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        // Inflate the layout for this fragment
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.back_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                NavigationUI.onNavDestinationSelected(
                    menuItem,
                    requireActivity().findNavController(R.id.loginFragment)
                )
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        binding.lifecycleOwner = this
        binding.shoesViewModel = viewModel
        return binding.root
    }


}