package com.example.autoweightselector

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.autoweightselector.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

 //   var myViewModel = MainActivity.myViewModel


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
 //    val myViewModel = activity?.run {
 //           ViewModelProvider(this).get(MyViewModel::class.java)
  //      } ?: throw Exception("Invalid Activity")

              //Databinding 綁定
        //  val databinding1 =
        val dataBinding = FragmentMainBinding.inflate(inflater, container, false)

  //      dataBinding.setData(myViewModel)
        dataBinding.setLifecycleOwner(this)
        return dataBinding.root
        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters

        var getrightqrcodemacvalue01 = "null"
        lateinit var myViewModel: MyViewModel

                @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textViewTest.text = myViewModel.testtext
        try {
            Log.d(TAG, "MainActivity.myViewModel ${myViewModel}")
        } catch (e:Exception) {
            Log.d(TAG, "onActivityCreated: ${e.printStackTrace()}")}
//        val myViewModel = MainActivity.myViewModel
        //myViewModel 綁定
 //        myViewModel = activity?.run {
//            ViewModelProvider(this).get(MyViewModel::class.java)
 //     } ?: throw Exception("Invalid Activity")

        //內容寫在此
        //      myViewModel.CheckBt()
        //      myViewModel.Connect()
 //       Log.d(TAG, "myViewModea:$myViewModel ")
        Log.d(TAG, "getrightqrcodemacvalue01: $getrightqrcodemacvalue01")
 //       Log.d(TAG, "getrightqrcodemacvalue00: ${myViewModel.getrightqrcodemacvalue}")




        //底下是測試碼
        btnScanQRCode.setOnClickListener {

            textViewTest.text = myViewModel.testtext
            var intent1 = Intent(context, QRCodeMainActivity::class.java)

            var str = "I am yct"
//用資料捆傳遞資料
//用資料捆傳遞資料
            val bundle = Bundle()
            bundle.putString("data", str)
//把資料捆設定改意圖
            intent1.putExtra("bun", bundle)  //傳遞的資料
//啟用意圖
            startActivity(intent1)

        }

        btnExit.setOnClickListener {
            findNavController().navigate(R.id.doFragment)
        }

        btnConnect.setOnClickListener {
            //說話
   //         myViewModel.texttospeech("Welcome")
        }
    }


}