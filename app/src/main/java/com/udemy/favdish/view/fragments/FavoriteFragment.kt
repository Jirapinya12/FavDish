package com.udemy.favdish.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.udemy.favdish.application.FavDishApplication
import com.udemy.favdish.databinding.FragmentFavoriteBinding
import com.udemy.favdish.viewmodel.DashboardViewModel
import com.udemy.favdish.viewmodel.FavDishViewModel
import com.udemy.favdish.viewmodel.FavDishViewModelFactory

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    private val mFavDishViewModel: FavDishViewModel by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(DashboardViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFavDishViewModel.favoriteDishesList.observe(viewLifecycleOwner) {
            dishes ->
            dishes.let {
                if (it.isNotEmpty()) {
                    for (dish in it) {
                        Log.i("Favorite Dish", "${dish.id} :: ${dish.title}")
                    }
                } else {
                    Log.i("List of Favorite Dishes", "is empty")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}