package com.udemy.favdish.view.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.udemy.favdish.R
import com.udemy.favdish.databinding.ActivityAddUpdateDishBinding

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
                    Toast.makeText(
                        this@AddUpdateDishActivity,
                        "You have clicked the ImageView",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
            }
        }
    }
}