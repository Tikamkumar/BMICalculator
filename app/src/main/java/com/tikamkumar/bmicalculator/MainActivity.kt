package com.tikamkumar.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tikamkumar.bmicalculator.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            bmiCalculateFunc()
        }

        binding.resetBtn.setOnClickListener {
            binding.edtWeight.text.clear()
            binding.edtHeightFt.text.clear()
            binding.edtHeightInch.text.clear()
        }
    }

    private fun bmiCalculateFunc() {
        if (binding.edtWeight.text.toString() == "") {
            binding.edtWeight.error = "Enter Weight.."
        } else if (binding.edtHeightFt.text.toString() == "") {
            binding.edtHeightFt.error = "Enter Height(In Ft).."
        } else if (binding.edtHeightInch.text.toString() == "") {
            binding.edtHeightInch.error = "Enter Height(In Inch).."
        } else {
            val wt = binding.edtWeight.text.toString().toInt()
            val hft = binding.edtHeightFt.text.toString().toInt()
            val htIn = binding.edtHeightInch.text.toString().toInt()
            val totalInch = hft * 12 + htIn
            val totalCm = totalInch * 2.53
            val totalM = totalCm / 100
            val bmi = wt / (totalM * totalM)

            if (bmi > 25) {
                binding.txtResult.text = getString(R.string.msg_overweight)
                binding.txtResult.setBackgroundColor(resources.getColor(R.color.colorOW))
            } else if (bmi < 18) {
                binding.txtResult.text = getString(R.string.msg_underweight)
                binding.txtResult.setBackgroundColor(resources.getColor(R.color.colorUW))
            } else {
                binding.txtResult.text = getString(R.string.msg_healthy)
                binding.txtResult.setBackgroundColor(resources.getColor(R.color.colorH))
            }
        }
    }
}