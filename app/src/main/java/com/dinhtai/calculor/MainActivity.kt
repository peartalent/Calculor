package com.dinhtai.calculor

import android.graphics.Point
import android.os.Bundle
import android.view.Display
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var stt: Int = 0 // state :1 nhap toán hạng 1,2 là nhập toán hạng 2
    var op0: Int = 0
    var op1: Int = 0
    var op2: Int = 0
    val ADD_OPERAND =1 // cộng
    val SUB_OPERAND =2 // trừ
    val MUL_OPERAND =3 // nhân
    val DIV_OPERAND =4 // chia
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        assignViews(listOf(btn_0, btn_1, btn_2, btn_3,btn_4, btn_5,
                btn_6, btn_7, btn_8, btn_9, btn_dot, btn_BS, btn_C, btn_CE,
                btn_div, btn_add, btn_mul, btn_equal, btn_rev, btn_sub))
        var display = getWindowManager().getDefaultDisplay();
        var size = Point();
        display.getSize(size);
        txt_result.maxWidth = size.x
        initInitialization()
    }

    //Extenstion set hết view
    fun View.OnClickListener.assignViews(views: List<View?>) {
        views?.forEach { it?.setOnClickListener(this) }
    }
//    fun View.OnClickListener.assignViews(vararg views: View?) {
//        views.forEach { it?.setOnClickListener(this) }
//    }

//    private fun initListeners() {
//        listOf(btn_0,btn_1).forEach { view ->
//            view?.setOnClickListener(this)
//        }
//    }

    private fun initInitialization() {
        stt = 1;
        op2 = 0
        op1 =0
        op0 = 0
        txt_result.text = op1.toString()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_0 -> addDigit(0)
            R.id.btn_1 -> addDigit(1)
            R.id.btn_2 -> addDigit(2)
            R.id.btn_3 -> addDigit(3)
            R.id.btn_4 -> addDigit(4)
            R.id.btn_5 -> addDigit(5)
            R.id.btn_6 -> addDigit(6)
            R.id.btn_7 -> addDigit(7)
            R.id.btn_8 -> addDigit(8)
            R.id.btn_9 -> addDigit(9)
            R.id.btn_BS -> {
                removeDigit()
            }
            R.id.btn_C -> {
                clearALl()
            }
            R.id.btn_CE -> {
                clearOperand()
            }
            R.id.btn_dot -> {

            }
            R.id.btn_add -> {
                selectOperand(ADD_OPERAND)
            }
            R.id.btn_sub -> {
                selectOperand(SUB_OPERAND)
            }
            R.id.btn_mul -> {
                selectOperand(MUL_OPERAND)
            }
            R.id.btn_div -> {
                selectOperand(DIV_OPERAND)
            }
            R.id.btn_equal -> {
                calculateResult()
            }
            R.id.btn_rev -> {
                reverse()
            }
        }
    }
 // thêm số hạng vào
    private fun addDigit(digit: Int) {
        if (stt == 1) {
            var sign: Int = if (op1 < 0) -1 else 1
            op1 = op1 * 10 + sign * digit
            txt_result.text = op1.toString()

        } else {
            var sign: Int = if (op2 < 0) -1 else 1
            op2 = op2 * 10 + sign * digit
            txt_result.text = op2.toString()
        }
    }
    //gán phép toán và chuyển sang trạng thái khác
    private fun selectOperand (operand : Int){
        stt =2
        op0 = operand
    }
    // đổi dấu
    private fun reverse(){
        if(stt ==1){
            op1 = -op1
            txt_result.text =op1.toString()
        } else {
            op2 = -op2
            txt_result.text = op2.toString()
        }
    }
    // xoa số hạng
    private fun removeDigit(){
        if(stt ==1){
            op1 /=10
            txt_result.text =op1.toString()
        }else {
            op2 /= 10
            txt_result.text = op2.toString()
        }
    }

    // xoa cả
    private fun clearALl(){
        initInitialization()
    }
    // xóa toán tử
    private fun clearOperand(){
        if(stt ==1){
            op1 =0;
            txt_result.text = op1.toString()
        }
        else {
            op2 =0
            txt_result.text = op2.toString()
        }
    }


    private fun calculateResult(){
        var rs =0
        when(op0){
            ADD_OPERAND -> rs = op1 + op2
            SUB_OPERAND -> rs = op1 - op2
            MUL_OPERAND -> rs = op1 * op2
            DIV_OPERAND -> {
                if(op2 == 0) txt_result.text ="ERROR"
                else rs = op1 /op2
            }
        }
        if (!(op0 ==4 && op0 ==0)){
            txt_result.text = rs.toString()
        }
        stt = 1
        op1 =0
        op0 = 0
        op2 =0
    }
}
