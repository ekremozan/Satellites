package ekrem.ozan.satellites.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ekrem.ozan.satellites.R
import ekrem.ozan.satellites.base.extensions.addVerticalDivider
import ekrem.ozan.satellites.base.extensions.observe
import ekrem.ozan.satellites.base.extensions.runContextNotNull
import ekrem.ozan.satellites.base.extensions.textChangeListener
import ekrem.ozan.satellites.base.view.BaseFragment
import ekrem.ozan.satellites.databinding.FragmentListBinding
import ekrem.ozan.satellites.domain.model.SatelliteData
import ekrem.ozan.satellites.ui.list.adapter.SatelliteAdapter
import ekrem.ozan.satellites.util.Constants.Companion.ITEM_DECORATOR_HORIZONTAL_MARGIN
import ekrem.ozan.satellites.util.Constants.Companion.ITEM_DECORATOR_VERTICAL_MARGIN

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(R.layout.fragment_list) {
    override val viewModel: ListViewModel by viewModels()
    private val satelliteAdapter: SatelliteAdapter by lazy { SatelliteAdapter { onItemClick(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) initStartRequest()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeViewModelFields()
        addItemDecorator()
        setOnClickListeners()
    }

    private fun setUpViews() {
        binding.satelliteList.adapter = satelliteAdapter
    }

    private fun initStartRequest() {
        runContextNotNull { context -> viewModel.getSatellites(context) }
    }

    private fun setOnClickListeners() {
        searchViewClick()
    }

    private fun observeViewModelFields() {
        observe(viewModel.satelliteListLiveData) { satelliteAdapter.submitList(it) }
        observe(viewModel.uiStateLiveData) { binding.uiState = it }
    }

    private fun searchViewClick() {
        binding.searchView.textChangeListener { filter ->
            if (filter != null && filter.isNotEmpty())
                viewModel.satelliteList.filter { satellite -> satellite.name.lowercase().startsWith(filter.lowercase()) }.apply {
                    satelliteAdapter.submitList(this)
                }
            else if (filter != null && filter.isEmpty())
                satelliteAdapter.submitList(viewModel.satelliteList)
        }
    }

    private fun onItemClick(satellite: SatelliteData) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(satellite.id, satellite.name))
    }

    private fun addItemDecorator() {
        runContextNotNull { binding.satelliteList.addVerticalDivider(it, R.drawable.divider, ITEM_DECORATOR_HORIZONTAL_MARGIN, ITEM_DECORATOR_VERTICAL_MARGIN, ITEM_DECORATOR_HORIZONTAL_MARGIN, ITEM_DECORATOR_VERTICAL_MARGIN) }
    }
}
