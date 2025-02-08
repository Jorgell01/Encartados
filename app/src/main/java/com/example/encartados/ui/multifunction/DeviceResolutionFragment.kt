// DeviceResolutionFragment.kt
package com.example.encartados.ui.multifunction

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.encartados.databinding.FragmentDeviceResolutionBinding

class DeviceResolutionFragment : Fragment() {

    private var _binding: FragmentDeviceResolutionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeviceResolutionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels

        binding.textResolution.text = "Resolution: ${width}x${height}"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}