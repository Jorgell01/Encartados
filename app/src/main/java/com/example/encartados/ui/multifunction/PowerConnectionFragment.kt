// PowerConnectionFragment.kt
package com.example.encartados.ui.multifunction

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.encartados.databinding.FragmentPowerConnectionBinding

class PowerConnectionFragment : Fragment() {

    private var _binding: FragmentPowerConnectionBinding? = null
    private val binding get() = _binding!!
    private lateinit var powerReceiver: BroadcastReceiver
    private var isReceiverRegistered = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPowerConnectionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        powerReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent?.action
                if (action == Intent.ACTION_POWER_CONNECTED) {
                    binding.textPowerStatus.text = "Power Connected"
                } else if (action == Intent.ACTION_POWER_DISCONNECTED) {
                    binding.textPowerStatus.text = "Power Disconnected"
                }
            }
        }

        binding.buttonToggleReceiver.setOnClickListener {
            if (isReceiverRegistered) {
                requireActivity().unregisterReceiver(powerReceiver)
                binding.textPowerStatus.text = "Receiver Unregistered"
            } else {
                val filter = IntentFilter().apply {
                    addAction(Intent.ACTION_POWER_CONNECTED)
                    addAction(Intent.ACTION_POWER_DISCONNECTED)
                }
                requireActivity().registerReceiver(powerReceiver, filter)
                binding.textPowerStatus.text = "Receiver Registered"
            }
            isReceiverRegistered = !isReceiverRegistered
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (isReceiverRegistered) {
            requireActivity().unregisterReceiver(powerReceiver)
        }
        _binding = null
    }
}