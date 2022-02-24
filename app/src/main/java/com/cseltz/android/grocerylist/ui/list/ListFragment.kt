package com.cseltz.android.grocerylist.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cseltz.android.grocerylist.NavGraphDirections
import com.cseltz.android.grocerylist.R
import com.cseltz.android.grocerylist.data.Item
import com.cseltz.android.grocerylist.di.MainApplication
import com.cseltz.android.grocerylist.ui.list.recyclerview.ListFragmentAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.collect

class ListFragment: Fragment(), ListFragmentAdapter.OnRecyclerViewItemClick {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        viewModel = (activity?.application as MainApplication).appContainer
            .viewModelFactory.create(ListViewModel::class.java)


        recyclerView = view.findViewById(R.id.main_recycler_view)
        fab = view.findViewById(R.id.main_fab)

        val adapter = ListFragmentAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.itemEvent.collect { event ->
                when (event) {
                    is UiEvents.ShowDeleteConfirmation -> {
                        findNavController().navigate(NavGraphDirections.actionGlobalDeleteDialog(event.item))
                    }
                    is UiEvents.NavigateToAddScreen -> {
                        findNavController().navigate(ListFragmentDirections.actionListFragmentToAddEditFragment(item = null))
                    }
                    is UiEvents.NavigateToEditScreen -> {
                        findNavController().navigate(ListFragmentDirections.actionListFragmentToAddEditFragment(item = event.item))
                    }
                    is UiEvents.ShowDeleteAllConfirmation -> {
                        findNavController().navigate(NavGraphDirections.actionGlobalDeleteAllDialog())
                    }
                    is UiEvents.NavigateToAboutScreen -> {
                        findNavController().navigate(NavGraphDirections.actionGlobalAboutFragment())
                    }
                }
            }
        }

        fab.setOnClickListener {
            viewModel.performEvent(ListItemEvents.OnAddFabClicked)
        }

        setHasOptionsMenu(true)

        return view
    }

    // RecyclerView custom interface
    override fun onLongPress(item: Item) {
        viewModel.performEvent(ListItemEvents.OnLongClick(item))
    }

    override fun onCheckedChanged(item: Item, isChecked: Boolean) {
        viewModel.performEvent(ListItemEvents.OnChecked(item, isChecked))
    }

    override fun onClick(item: Item) {
        viewModel.performEvent(ListItemEvents.OnClick(item))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete_all -> {
                viewModel.performEvent(ListItemEvents.OnDeleteAllClicked)
                true
            }

            R.id.menu_about -> {
                viewModel.performEvent(ListItemEvents.OnAboutClicked)
                true
            }
            else -> false
        }
    }
}