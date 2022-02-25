package com.example.financialcalculator



import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    private lateinit var pa: EditText
    private lateinit var rate: EditText
    private lateinit var ta: EditText
    private lateinit var n: EditText
    private lateinit var fa: EditText
    private lateinit var si: EditText
    private lateinit var ci: EditText
    private lateinit var sir: EditText
    private lateinit var cir: EditText







    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Financial calc"
        val calc = findViewById<Button>(R.id.calculate)
        val clear = findViewById<Button>(R.id.clear)
        pa = findViewById(R.id.princi)
        rate = findViewById(R.id.rate)
        ta = findViewById(R.id.tenure)
        n = findViewById(R.id.n)
        fa = findViewById(R.id.F)
        si = findViewById(R.id.SI)
        ci = findViewById(R.id.CI)
        sir = findViewById(R.id.per)
        cir = findViewById(R.id.CAGR)




        clear.setOnClickListener{
            fa.setText("")
            si.setText("")
            ci.setText("")
            sir.setText("")
            cir.setText("")
            n.setText("")
            pa.setText("")
            ta.setText("")
            rate.setText("")

        }


        calc.setOnClickListener {

            val Pa: String = pa.text.toString()
            val principalamnt: Double? = Pa.toDouble()

            val ra: String = rate.text.toString()
            val rate: Double? = ra.toDouble()/100

            val t: String = ta.text.toString()
            val time: Double? = t.toDouble()

            val num: String = n.text.toString()
            val Num: Double? = num.toDouble()

            var c_interest = coumpound_intrest(principalamnt!!, rate!!,time!!,Num!!)
            var final_value = final_amount(principalamnt, c_interest)



            var c_interest_int = coumpound_intrest(principalamnt, rate,time,Num).roundToInt()
            var final_value_int: Int? = final_amount(principalamnt, c_interest).roundToInt()
            var s_interest = simple_intrest(principalamnt, rate, time).roundToInt()
            var si_percentage = si_percent(final_value,principalamnt,time).roundToInt()
            var cagr_percentage = cagr_percent(principalamnt,final_value,time,Num).roundToInt()

            fa.setText("Rs. ${final_value_int}")
            si.setText("Rs. ${s_interest}")
            ci.setText("Rs. ${c_interest_int}")
            sir.setText("Rs. ${si_percentage}%")
            cir.setText("Rs. ${cagr_percentage}%")
        }



    }
    fun final_amount(p: Double,c: Double): Double {
        return  p+c

    }
    fun simple_intrest(p: Double,r: Double,t: Double): Double{
        return p*r*t
    }
    fun coumpound_intrest(p: Double,r: Double,t: Double,n: Double): Double {
        return p*((1+r/n).pow(n*t))-p
    }
    fun si_percent(f: Double, p: Double, t: Double): Double{
        return ((f-p)/(p*t))*100
    }
    fun cagr_percent(p: Double,f: Double,t: Double,n: Double): Double{
        return (n*((f/p).pow(1/(n*t)))-1)*100
    }




}


