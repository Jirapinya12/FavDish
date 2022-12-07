package com.udemy.favdish.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.udemy.favdish.R
import com.udemy.favdish.databinding.FragmentDishDetailsBinding
import java.io.IOException
import java.util.*

class DishDetailsFragment : Fragment() {


    private var mBinding: FragmentDishDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDishDetailsBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DishDetailsFragmentArgs by navArgs()

        mBinding?.apply {
            args.let {
                try {
                    Glide.with(requireActivity())
                        .load(it.dishDetails.image)
                        .centerCrop()
                        .into(ivDishImage)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                tvTitle.text = it.dishDetails.title
                tvType.text =
                    it.dishDetails.type.capitalize(Locale.ROOT) // Used to make first letter capital
                tvCategory.text = it.dishDetails.category
                tvIngredients.text = it.dishDetails.ingredients
                tvCookingDirection.text = it.dishDetails.direction_to_cook
                tvCookingTime.text =
                    resources.getString(
                        R.string.lbl_estimate_cooking_time,
                        it.dishDetails.cooking_time
                    )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}