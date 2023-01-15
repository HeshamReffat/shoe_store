package com.hishamreffat.shoestore.screens.shoes

import android.os.Bundle
import android.util.Log
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
import androidx.navigation.findNavController
import com.hishamreffat.shoestore.R
import com.hishamreffat.shoestore.database.shoes.ShoeItemDatabase
import com.hishamreffat.shoestore.databinding.FragmentShoesListBinding
import com.hishamreffat.shoestore.models.shoes.ShoeViewModel
import com.hishamreffat.shoestore.models.shoes.ShoeViewModelFactory


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

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        // Inflate the layout for this fragment
        viewModel.shoes.observe(viewLifecycleOwner, Observer { shoe ->
            binding.shoeLinearLayout.removeAllViews()
            shoe.forEach {
                Log.i("fargment", "${it?.shoeName}")
                val newShoe = TextView(context)
                newShoe.text = it?.shoeName
                newShoe.textSize = 22F
                newShoe.gravity = Gravity.CENTER
                newShoe.setLayoutParams(
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ),
                )
                binding.shoeLinearLayout.addView(newShoe)
//            if (newShoe.getParent() != null) {
//                (newShoe.getParent() as ViewGroup).removeView(newShoe) // <- fix
                //}
            }
        })
        binding.shoeDetailsButton.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailsFragment())
        }
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.back_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.loginFragment -> {
                        view?.findNavController()
                            ?.navigate(ShoesListFragmentDirections.actionShoesListFragmentToLoginFragment2())
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        binding.lifecycleOwner = this
        binding.shoesViewModel = viewModel
        return binding.root
    }


}