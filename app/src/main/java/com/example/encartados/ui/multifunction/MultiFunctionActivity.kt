// MultiFunctionActivity.kt
package com.example.encartados.ui.multifunction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.encartados.databinding.ActivityMultiFunctionBinding
import com.google.android.material.tabs.TabLayoutMediator

class MultiFunctionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMultiFunctionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiFunctionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MultiFunctionPagerAdapter(this)
        binding.viewPager.adapter = adapter

        val tabTitles = listOf("Resolution", "Power", "Light", "Location")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}