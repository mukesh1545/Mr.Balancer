package com.example.mrbalancer

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrbalancer.Adapter.adapter
import com.example.mrbalancer.RoomDataBase.BalancerData
import com.example.mrbalancer.RoomDataBase.BalancerDataBase
import com.example.mrbalancer.Util.RupessFormat
import com.example.mrbalancer.ViewModel.Balancer_ViewModel
import com.example.mrbalancer.ViewModel.Balancer_ViewProvider
import com.example.mrbalancer.ViewModel.Balancer_repo
import com.example.mrbalancer.databinding.FragmentBalancerDisplayPageBinding



class Balancer_DisplayPage : Fragment() {
 private  var _binding:FragmentBalancerDisplayPageBinding?=null
    lateinit var  ViewModel:Balancer_ViewModel
    var balances:Int=0
    private val  binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBalancerDisplayPageBinding.inflate(inflater, container, false)



        var Dao=BalancerDataBase.getInstances(this.requireContext())?.BalancerDao()
        var repo=Balancer_repo(Dao!!)
        ViewModel =ViewModelProvider(this, Balancer_ViewProvider(repo)).get(Balancer_ViewModel::class.java)
        binding.balances.text= balances.toString()
        binding.Outgoing.text=balances.toString()
        binding.Income.text=balances.toString()
        binding.recyclerView.layoutManager=LinearLayoutManager(this.requireContext())
        Log.d("Test","${ViewModel.getAllData()}")
        ViewModel.getAllData().observe(viewLifecycleOwner, Observer {
            Log.d("Test","$it")
                var Adapter= adapter(it)
                binding.recyclerView.adapter = Adapter
        })
        ViewModel.Incoming().observe(viewLifecycleOwner, Observer {

            if(it==null)
            {
                binding.Income.text=RupessFormat.rupessConverter(0)
            }
            else {
              binding.Income.text = RupessFormat.rupessConverter(it)
            }

        })
        ViewModel.outgoing().observe(viewLifecycleOwner, Observer {

            if(it==null)
            {
                binding.Outgoing.text=RupessFormat.rupessConverter(0)
            }
            else {
                binding.Outgoing.text = RupessFormat.rupessConverter(it)
            }
        })

        ViewModel.Balances().observe(viewLifecycleOwner, Observer {
            if(it==null)
            {
                binding.balances.text=RupessFormat.rupessConverter(0)
            }
            else
            {
                binding.balances.text = RupessFormat.rupessConverter(it)
            }
        })

        return _binding?.root

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            val AddFragment=Balancer_AddPage()
          requireActivity().supportFragmentManager.beginTransaction()
              .replace(R.id.fr,AddFragment)
            .addToBackStack(null)
              .commit()
        }
    }
}