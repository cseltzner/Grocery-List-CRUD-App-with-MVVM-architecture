package com.cseltz.android.grocerylist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cseltz.android.grocerylist.R
import com.cseltz.android.grocerylist.di.MainApplication

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}