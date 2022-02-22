package com.cseltz.android.grocerylist.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cseltz.android.grocerylist.R
import com.cseltz.android.grocerylist.data.Item
import com.cseltz.android.grocerylist.di.MainApplication
import com.cseltz.android.grocerylist.ui.MainActivity
import com.cseltz.android.grocerylist.ui.list.recyclerview.ListFragmentAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val viewModel = (activity?.application as MainApplication).appContainer
            .listViewModelFactory.create(ListViewModel::class.java)


        recyclerView = view.findViewById(R.id.main_recycler_view)
        fab = view.findViewById(R.id.main_fab)

        val adapter = ListFragmentAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

        return view
    }
}