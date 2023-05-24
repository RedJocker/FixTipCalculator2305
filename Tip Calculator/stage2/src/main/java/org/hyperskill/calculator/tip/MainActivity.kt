package org.hyperskill.calculator.tip

import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    lateinit var seekBar: SeekBar
    lateinit var editText: EditText
    lateinit var billValueTv: TextView
    lateinit var tipPercentTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        billValueTv = findViewById(R.id.bill_value_tv)
        tipPercentTv = findViewById(R.id.tip_percent_tv)
        editText = findViewById(R.id.edit_text)
        seekBar = findViewById(R.id.seek_bar)

        editText.addTextChangedListener {
            it.toString().toBigDecimalOrNull().also { maybeBillValue ->
                updateDisplay(billValue = maybeBillValue)
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateDisplay(tipPercent = progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        })
    }

    private fun updateDisplay(
        billValue: BigDecimal? = editText.text.toString().toBigDecimalOrNull(),
        tipPercent: Int = seekBar.progress) {


        if(billValue != null && billValue > BigDecimal.ZERO) {
            billValueTv.text = "Bill Value: \$%.2f".format(billValue)
            tipPercentTv.text = "Tip: $tipPercent%"
        } else {
            billValueTv.text = ""
            tipPercentTv.text = "b"
        }
    }
}