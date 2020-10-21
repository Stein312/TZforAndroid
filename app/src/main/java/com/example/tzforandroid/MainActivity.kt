package com.example.tzforandroid

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    var coming=0.0f
    var expenditure=0.0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val remains=findViewById<TextView>(R.id.textRemains)
        val editTextComing=findViewById<EditText>(R.id.etCom)
        val inputTextComing=findViewById<TextInputLayout>(R.id.textInputLayout)

        val editTextExpenditure=findViewById<EditText>(R.id.etExpenditure)
        val inputLayoutExpenditure=findViewById<TextInputLayout>(R.id.textInputLayout2)


        editTextComing.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(editable: Editable?) {
                val str= editable?.toString() ?:""
                if(str!=""){
                if(str.indexOfAny(charArrayOf('.', ',')) >= 0){
                    str.replace(Regex("""[$,.]"""), "")
                }
                coming=str.toFloatOrNull() ?:0.0f
                if(editTextExpenditure.text.isNotEmpty())
                    if(inRange(str.toFloat())){
                        inputTextComing.error=""
                        remains.text= calc()
                        if(remains.text.isEmpty()){
                            inputTextComing.error="Приход не может быть меньше расхода"
                        remains.text=""}
                        else
                            inputTextComing.error=""
                        }
                    else{
                        inputTextComing.error="Число должно быть в диапазоне от 0 до 100000"
                        remains.text=""
                    }
                }
                else{
                    coming=0.0f
                    remains.text=""
                }

            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                // действия перед тем, как что то введено
            }
        })
        editTextExpenditure.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(editable: Editable?) {
                val str= editable?.toString() ?:""
                if(str!=""){
                if(str.indexOfAny(charArrayOf('.', ',')) >= 0){
                    str.replace(Regex("""[$,.]"""), "")
                }
                expenditure=str.toFloatOrNull() ?:0.0f
                if(editTextComing.text.isNotEmpty())
                    if(inRange(str.toFloat())){
                        inputLayoutExpenditure.error=""
                        remains.text= calc()
                        if(remains.text.isEmpty()){
                            inputTextComing.error="Приход не может быть меньше расхода"
                           remains.text=""}
                        else
                            inputTextComing.error=""
                    }
                    else{
                        inputLayoutExpenditure.error="Число должно быть в диапазоне от 0 до 100000"
                        remains.text=""
                    }}
                else
                    remains.text=""

            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                // действия перед тем, как что то введено
            }
        })

    }
    fun calc():String{
        if(coming<expenditure){
            return ""
        }
        val str=(coming-expenditure).toString()
        Log.d("calc",str)
        if(str.indexOfAny(charArrayOf('.'))<=0){
        val arrStr=str.split("\\.")
        if(arrStr[1].length>=6){
        return String.format("%.5f",coming-expenditure)}
        }
        return str

    }
    private fun inRange(a: Float):Boolean{
        return a in 0.0..100000.0

    }

}