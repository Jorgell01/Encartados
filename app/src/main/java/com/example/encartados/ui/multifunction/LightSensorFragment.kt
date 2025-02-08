package com.example.encartados.ui.multifunction

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.encartados.databinding.FragmentLightSensorBinding

class LightSensorFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentLightSensorBinding? = null
    private val binding get() = _binding

    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLightSensorBinding.inflate(inflater, container, false)

        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        if (lightSensor == null) {
            binding?.textLightLevel?.text = "No hay sensor de luz disponible."
            binding?.textLightStatus?.text = "No se puede medir la iluminación."
        }

        return binding!!.root
    }

    override fun onResume() {
        super.onResume()
        lightSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val lightLevel = event.values[0]
            binding?.textLightLevel?.text = "Nivel de luz: $lightLevel lx"
            binding?.textLightStatus?.text = when {
                lightLevel < 100 -> "Oscuro"
                lightLevel.toInt() in 100..2000 -> "Normal" // Convertimos a Int
                else -> "Brillante"
            }

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No se necesita implementar
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
