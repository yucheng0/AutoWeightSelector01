package com.example.autoweightselector

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.autoweightselector.databinding.FragmentDoBinding
import kotlinx.android.synthetic.main.fragment_do.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //myViewModel 綁定
        val myViewModel = activity?.run {
            ViewModelProvider(this).get(MyViewModel::class.java) } ?:throw Exception ("Invalid Activity")
        //Databinding 綁定
        //  val databinding1 =
        val dataBinding = FragmentDoBinding.inflate(inflater,container,false)

        //內容寫在此

        dataBinding.setData(myViewModel)
        dataBinding.setLifecycleOwner(this)
        return dataBinding.root

        // Inflate the layout for this fragment
    //    return inflater.inflate(R.layout.fragment_do, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //內容寫在這
        //myViewModel 綁定
        val myViewModel = activity?.run {
            ViewModelProvider(this).get(MyViewModel::class.java) } ?:throw Exception ("Invalid Activity")
//藍芽處理程序
        myViewModel.weightflashState.observe(viewLifecycleOwner, {
            if (myViewModel.isPowerOnEnable == true) {
                if (myViewModel.isWeightFlash == true) {
                   when (myViewModel.weightflashState.value){
                       true  -> textViewWeight.text = "100"
                       false ->  textViewWeight.text = ""  }
                }
            }
        })

        //監聽  ->第1次初始化MyViewModel時它的值是被監聽到的所以它第一次會被執行
        myViewModel.diff.observe(viewLifecycleOwner,  {
            if (myViewModel.isPowerOnEnable == true) {
                myViewModel.StartTimer()
                var minutes = (myViewModel.diff.value)?.div(60).toString()
                var second = (myViewModel.diff.value)?.rem(60).toString()
                if (minutes.toInt() <= 9) {
                    minutes = "0" + minutes
                }
                if (second.toInt() <= 9) {
                    second = "0" + second
                }
                var time1 = "${minutes}:${second}"
                textViewTime.text = time1
            }
                })


        btnTest.setOnClickListener {
            val intent = Intent (context,MainActivity2::class.java)
            startActivity(intent)
        }

//底下是測試碼
        textViewTime.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
        }
        btnStart.setOnClickListener {
            myViewModel.isWeightFlash = true  //0830test
            if (myViewModel.isPowerOnEnable == false) {
                myViewModel.isPowerOnEnable = true
                myViewModel.now = SystemClock.uptimeMillis()
                myViewModel.StartTimer()    //啟動時間
            }
        }
    }
}