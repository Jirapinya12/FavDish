package com.udemy.favdish.view.activities

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.udemy.favdish.R
import com.udemy.favdish.databinding.ActivityAddUpdateDishBinding
import com.udemy.favdish.databinding.DialogCustomImageSelectionBinding

class AddUpdateDishActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityAddUpdateDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupActionBar()
        mBinding.ivAddDishImage.setOnClickListener(this)
    }

    private fun setupActionBar() {
        setSupportActionBar(mBinding.toolbarAddDish)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.toolbarAddDish.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onClick(v: View?) {
        v?.apply {
            when (v.id) {
                R.id.iv_add_dish_image -> {
                    customImageSelectionDialog()
                    return
                }
            }
        }
    }

    private fun customImageSelectionDialog() {
        val dialog = Dialog(this@AddUpdateDishActivity)

        val binding: DialogCustomImageSelectionBinding =
            DialogCustomImageSelectionBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        binding.llLayoutCamera.setOnClickListener {
            Toast.makeText(
                this@AddUpdateDishActivity,
                "You have clicked on the Camera.",
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }
        binding.llLayoutGallery.setOnClickListener {
            Toast.makeText(
                this@AddUpdateDishActivity,
                "You have clicked on the Gallery.",
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }
        dialog.show()
    }
}