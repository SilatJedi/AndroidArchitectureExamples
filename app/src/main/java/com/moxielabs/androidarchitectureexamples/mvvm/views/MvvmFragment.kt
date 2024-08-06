package com.moxielabs.androidarchitectureexamples.mvvm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moxielabs.androidarchitectureexamples.R
import com.moxielabs.androidarchitectureexamples.mvc.controller.MvcFragment.ViewHolder
import com.moxielabs.androidarchitectureexamples.mvvm.viewmodel.MainViewModel

class MvvmFragment: Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_architecture, container, false)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        viewModel.fruits.observe(viewLifecycleOwner, Observer { fruits ->
            view.findViewById<RecyclerView>(R.id.recycler_view)?.apply {
                layoutManager = LinearLayoutManager(this.context)

                adapter = object : RecyclerView.Adapter<ViewHolder>() {
                    override fun getItemCount(): Int = fruits.size

                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                        return ViewHolder(
                            LayoutInflater.from(parent.context)
                                .inflate(R.layout.shared_list_item, parent, false)
                        )
                    }

                    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                        val fruit = fruits[position]

                        "${fruit.taste} ${fruit.color} ${fruit.name}".also { holder.text.text = it }
                    }
                }
            }
        })



        return view
    }
}