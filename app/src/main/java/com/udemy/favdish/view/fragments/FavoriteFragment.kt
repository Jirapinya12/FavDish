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
import androidx.recyclerview.widget.GridLayoutManager
import com.udemy.favdish.application.FavDishApplication
import com.udemy.favdish.databinding.FragmentFavoriteBinding
import com.udemy.favdish.view.adapters.FavDishAdapter
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
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFavDishViewModel.favoriteDishesList.observe(viewLifecycleOwner) {
            dishes ->
            dishes.let {
                _binding?.rvFavoriteDishesList?.layoutManager = GridLayoutManager(requireActivity(), 2)
                val adapter = FavDishAdapter(this)
                _binding?.rvFavoriteDishesList?.adapter = adapter
                if (it.isNotEmpty()) {
                    _binding?.rvFavoriteDishesList?.visibility = View.VISIBLE
                    _binding?.tvNoFavoriteDishesAvailable?.visibility = View.GONE
                    adapter.dishesList(it)
                } else {
                    _binding?.rvFavoriteDishesList?.visibility = View.GONE
                    _binding?.tvNoFavoriteDishesAvailable?.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}