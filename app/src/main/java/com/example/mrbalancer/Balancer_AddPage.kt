package com.example.mrbalancer

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mrbalancer.RoomDataBase.BalancerData
import com.example.mrbalancer.RoomDataBase.BalancerDataBase
import com.example.mrbalancer.ViewModel.Balancer_ViewModel
import com.example.mrbalancer.ViewModel.Balancer_ViewProvider
import com.example.mrbalancer.ViewModel.Balancer_repo
import com.example.mrbalancer.databinding.FragmentBalancerAddPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.properties.Delegates

class Balancer_AddPage : Fragment() {
    private var _binding: FragmentBalancerAddPageBinding? = null
    private val binding get() = _binding!!
    lateinit  var Amount: String
    lateinit var Date: String
    lateinit var Type: String
    lateinit var Tag1: String
    var remark: String="Not specified"
    private val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
     _binding= FragmentBalancerAddPageBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Add.setOnClickListener {
            if(validation())
            {
                var Dao=BalancerDataBase.getInstances(this.requireContext())!!.BalancerDao()
                var repo=Balancer_repo(Dao)
                var ViewModel = ViewModelProvider(this, Balancer_ViewProvider(repo)).get(Balancer_ViewModel::class.java)
                CoroutineScope(Dispatchers.IO).launch {
                    if(remark.isNullOrBlank())
                    {
                        remark="Not specified"
                    }
                    ViewModel.Insert(BalancerData(Amount!!.toInt(),Type,Tag1,Date,remark))
                }
                val AddFragment=Balancer_DisplayPage()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fr,AddFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
        binding.Date.inputType=0
        binding.Date.isFocusable=false
        binding.Date.isClickable=false
        binding.Date.setOnClickListener {
            showDatePickerDialog(it)
        }
    }
    private fun validation() :Boolean{

         Amount=binding.Amount.text.toString()
         Date=binding.Date.text.toString()

        if(Amount.isNullOrBlank()&& Date.isNullOrBlank())
        {
            Toast.makeText(requireContext(),"Enter valid Field",Toast.LENGTH_SHORT).show()
            return false
        }

        Tag1 = binding.Tag.selectedItem.toString()
        binding.Tag.onItemSelectedListener=object:AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
              Tag1 = parent!!.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        Type = binding.Type.selectedItem.toString()
        binding.Type.onItemSelectedListener=object:AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Type = parent!!.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        remark=binding.remark.text.toString()
        if(Tag1.equals("Others"))
        {
            if(remark.isNullOrBlank())
            {
                Toast.makeText(this.requireContext(),"Enter the remark",Toast.LENGTH_SHORT).show()
                return false
            }
        }
        remark=binding.remark.text.toString()
        Log.d("TEST_SPINNER","${Amount},${Date},${Type},${Tag1},${remark}")
        return true
    }

    fun showDatePickerDialog(view: View) {
        val datePickerDialog = DatePickerDialog(
            this.requireContext(),
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                // Set the selected date to the EditText
                calendar.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
              binding.Date.setText(dateFormat.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Show the date picker dialog
        datePickerDialog.show()
    }
}



