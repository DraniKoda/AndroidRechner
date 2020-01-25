package at.raphael.lehner.rechner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        tv_One.setOnClickListener { appendOnExpression("1", true) }
        tv_Two.setOnClickListener { appendOnExpression("2", true) }
        tv_Three.setOnClickListener { appendOnExpression("3", true) }
        tv_Four.setOnClickListener { appendOnExpression("4", true) }
        tv_Five.setOnClickListener { appendOnExpression("5", true) }
        tv_Six.setOnClickListener { appendOnExpression("6", true) }
        tv_Seven.setOnClickListener { appendOnExpression("7", true) }
        tv_Eight.setOnClickListener { appendOnExpression("8", true) }
        tv_Nine.setOnClickListener { appendOnExpression("9", true) }
        tv_Zero.setOnClickListener { appendOnExpression("0", true) }
        tv_Comma.setOnClickListener { appendOnExpression(".", true) }

        //Actions
        tv_Plus.setOnClickListener { appendOnExpression("+", false) }
        tv_Minus.setOnClickListener { appendOnExpression("-", false) }
        tv_Multiply.setOnClickListener { appendOnExpression("*", false) }
        tv_Division.setOnClickListener { appendOnExpression("/", false) }
        tv_Open.setOnClickListener { appendOnExpression("(", false) }
        tv_Close.setOnClickListener { appendOnExpression(")", false) }


        tv_Clear.setOnClickListener {
            tv_Input.text = ""
            tv_Output.text = ""
        }
        tv_Delete.setOnClickListener {
            val string = tv_Input.text.toString()
            if (string.isNotEmpty()) {
                tv_Input.text = string.substring(0, string.length - 1)
            }
            tv_Output.text = ""
        }
        tv_Equals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tv_Input.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    tv_Output.text = longResult.toString()
                else
                    tv_Output.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", "Message : " + e.message)
            }
        }


    }

    fun appendOnExpression(string: String, canClear: Boolean) {

        if (tv_Output.text.isNotEmpty()) {
            tv_Input.text = ""
        }
        if (canClear) {
            tv_Output.text = ""
            tv_Input.append(string)
        } else {
            tv_Input.append(tv_Output.text)
            tv_Input.append(string)
            tv_Output.text = ""
        }
    }
}
