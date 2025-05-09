package com.example.androidpracticumcustomview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.androidpracticumcustomview.ui.theme.CustomContainer


class XmlActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startXmlPracticum()
    }

    private fun startXmlPracticum() {
        val customContainer = CustomContainer(this)
        setContentView(customContainer)
        customContainer.setOnClickListener {
            finish()
        }

        val firstView = TextView(this).apply {
            text = getString(R.string.first_view_text)
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setBackgroundColor(getColor(R.color.purple_700))
        }
        customContainer.addView(firstView)

        val secondView = TextView(this).apply {
            text = getString(R.string.second_view_text)
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setBackgroundColor(getColor(R.color.teal_700))
        }

        Handler(Looper.getMainLooper()).postDelayed({
            customContainer.addView(secondView)
        }, 2000)
    }
}