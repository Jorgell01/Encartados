package com.example.encartados

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.IOException

class AudioRecorderActivity : AppCompatActivity() {

    private lateinit var btnRecord: Button
    private lateinit var btnPause: Button
    private lateinit var fabPlay: FloatingActionButton
    private lateinit var seekBarVolume: SeekBar
    private lateinit var seekBarFrequency: SeekBar

    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var outputFilePath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_recorder)

        btnRecord = findViewById(R.id.btnRecord)
        btnPause = findViewById(R.id.btnPause)
        fabPlay = findViewById(R.id.fabPlay)
        seekBarVolume = findViewById(R.id.seekBarVolume)
        seekBarFrequency = findViewById(R.id.seekBarFrequency)

        outputFilePath = getExternalFilesDir(Environment.DIRECTORY_MUSIC)?.absolutePath + "/audiorecordtest.3gp"

        btnRecord.setOnClickListener {
            startRecording()
        }

        btnPause.setOnClickListener {
            pauseRecording()
        }

        fabPlay.setOnClickListener {
            playRecording()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)
        }

        findViewById<Button>(R.id.buttonBackToHome).setOnClickListener {
            finish()
        }

    }

    private fun startRecording() {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(outputFilePath)
            try {
                prepare()
                start()
                Snackbar.make(btnRecord, "Recording started", Snackbar.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun pauseRecording() {
        mediaRecorder?.apply {
            stop()
            release()
            mediaRecorder = null
            Snackbar.make(btnPause, "Recording paused", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun playRecording() {
        mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(outputFilePath)
                prepare()
                setVolume(seekBarVolume.progress / 100f, seekBarVolume.progress / 100f)
                start()
                Snackbar.make(fabPlay, "Playing recording", Snackbar.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mediaRecorder?.release()
        mediaRecorder = null
        mediaPlayer?.release()
        mediaPlayer = null
    }
}