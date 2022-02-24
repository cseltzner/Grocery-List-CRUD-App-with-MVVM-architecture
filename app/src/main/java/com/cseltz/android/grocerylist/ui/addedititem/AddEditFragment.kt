package com.cseltz.android.grocerylist.ui.addedititem

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cseltz.android.grocerylist.R
import com.cseltz.android.grocerylist.data.Item
import com.cseltz.android.grocerylist.di.MainApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class AddEditFragment: Fragment() {

    private lateinit var titleText: TextView
    private lateinit var editName: EditText
    private lateinit var editQuantity: EditText
    private lateinit var fab: FloatingActionButton
    private lateinit var viewModel: AddEditViewModel
    val args: AddEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_item, container, false)

        viewModel = (activity?.application as MainApplication).appContainer.viewModelFactory.create(AddEditViewModel::class.java)
        if (args.item != null) {
            viewModel.initializeEdit(args.item!!)
        }

        titleText = view.findViewById(R.id.add_item_header)
        editName = view.findViewById(R.id.add_item_name)
        editQuantity = view.findViewById(R.id.add_item_quantity)
        fab = view.findViewById(R.id.add_item_fab)

        titleText.text = when (viewModel.currentItem == null) {
            true -> getString(R.string.add_item)
            else -> getString(R.string.edit_item)
        }
        editName.setText(viewModel.name ?: "")
        editQuantity.setText(viewModel.quantity ?: "1")

        editName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing
            }

            override fun afterTextChanged(newText: Editable?) {
                viewModel.name = newText.toString()
            }
        })

        editQuantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing
            }

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.quantity = newText.toString()
            }

            override fun afterTextChanged(newText: Editable?) {
                // Nothing
            }
        })

        lifecycleScope.launchWhenStarted {
            viewModel.itemEvent.collect { event ->
                when (event) {
                    is AddEditUiEvents.ShowSnackbar -> {
                        Snackbar.make(view, event.message, Snackbar.LENGTH_LONG).show()
                    }
                    is AddEditUiEvents.PopBackStack -> {
                        findNavController().popBackStack()
                    }
                }
            }
        }

        fab.setOnClickListener {
            viewModel.performEvent(AddEditEvents.OnAddEditFabClicked)
        }

        return view
    }
}