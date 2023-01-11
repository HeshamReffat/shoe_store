package com.hishamreffat.shoestore.screens.shoes

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoe ->
            for (i in shoe) {
                val newShoe = TextView(context)
                newShoe.text = "${i.name}"
                newShoe.textSize = 22F
                newShoe.gravity = Gravity.CENTER
                newShoe.setLayoutParams(
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ),
                )
//            if (newShoe.getParent() != null) {
//                (newShoe.getParent() as ViewGroup).removeView(newShoe) // <- fix
//            }
                binding.shoeLinearLayout.addView(newShoe)

            }
        })
        //val shoeText:TextView =TextView(this)
        // binding.shoeLinearLayout.addView(viewModel.shoeList)
        // Inflate the layout for this fragment
        val menuHost:MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.back_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                NavigationUI.onNavDestinationSelected(menuItem,requireActivity().findNavController(R.id.loginFragment))
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }


}