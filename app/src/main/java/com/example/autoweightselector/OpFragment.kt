package com.example.autoweightselector

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.autoweightselector.databinding.FragmentMainBinding
import com.example.autoweightselector.databinding.FragmentOpBinding
import kotlinx.android.synthetic.main.fragment_op.*
import java.io.IOException
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OpFragment : Fragment() {
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
        val dataBinding = FragmentOpBinding.inflate(inflater,container,false)

        //內容寫在此

        dataBinding.setData(myViewModel)
        dataBinding.setLifecycleOwner(this)
        return dataBinding.root

        // Inflate the layout for this fragment
//   return inflater.inflate(R.layout.fragment_op, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OpFragment.
         */
        // TODO: Rename and change types and number of parameters



        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OpFragment().apply {
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



//底下是測試碼
textView.setOnClickListener {
    findNavController().navigate(R.id.mainFragment)
}
    }





}