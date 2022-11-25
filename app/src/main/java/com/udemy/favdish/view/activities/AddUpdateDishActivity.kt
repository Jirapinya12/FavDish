package com.udemy.favdish.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.udemy.favdish.databinding.ActivityAddUpdateDishBinding

class AddUpdateDishActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAddUpdateDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(mBinding.toolbarAddDish)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.toolbarAddDish.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}