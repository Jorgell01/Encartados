// MultiFunctionPagerAdapter.kt
package com.example.encartados.ui.multifunction

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MultiFunctionPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        DeviceResolutionFragment(),
        PowerConnectionFragment(),
        LightSensorFragment(),
        LocationFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}