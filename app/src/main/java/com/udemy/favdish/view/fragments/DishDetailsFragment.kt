package com.udemy.favdish.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.udemy.favdish.R
import com.udemy.favdish.application.FavDishApplication
import com.udemy.favdish.databinding.FragmentDishDetailsBinding
import com.udemy.favdish.viewmodel.FavDishViewModel
import com.udemy.favdish.viewmodel.FavDishViewModelFactory
import java.io.IOException
import java.util.*

class DishDetailsFragment : Fragment() {


    private var mBinding: FragmentDishDetailsBinding? = null

    private val mFavDishViewModel: FavDishViewModel by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }

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
                        .listener(object : RequestListener<Drawable>{
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                Log.e("TAG", "ERROR loading Image", e)
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                resource?.let {
                                    Palette.from(resource.toBitmap()).generate() { palette ->
                                        val intColor = palette?.vibrantSwatch?.rgb ?: 0
                                        mBinding?.rlDishDetailMain?.setBackgroundColor(intColor)
                                    }
                                }
                                return false
                            }
                        })
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
                if (args.dishDetails.favorite_dish) {
                    mBinding?.ivFavoriteDish?.setImageDrawable(ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_favorite_selected
                    ))
                }
                else {
                    mBinding?.ivFavoriteDish?.setImageDrawable(ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_favorite_unselected
                    ))
                }
            }
        }

        mBinding?.ivFavoriteDish?.setOnClickListener {
            args.dishDetails.favorite_dish = !args.dishDetails.favorite_dish
            mFavDishViewModel.update(args.dishDetails)

            if (args.dishDetails.favorite_dish) {
                mBinding?.ivFavoriteDish?.setImageDrawable(ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_favorite_selected
                ))
                Toast.makeText(
                    requireActivity(),
                    resources.getString(R.string.msg_added_to_favorites),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                mBinding?.ivFavoriteDish?.setImageDrawable(ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_favorite_unselected
                ))
                Toast.makeText(
                    requireActivity(),
                    resources.getString(R.string.msg_removed_from_favorite),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}