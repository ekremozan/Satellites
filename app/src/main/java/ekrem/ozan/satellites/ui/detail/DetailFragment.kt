package ekrem.ozan.satellites.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ekrem.ozan.satellites.R
import ekrem.ozan.satellites.base.extensions.observe
import ekrem.ozan.satellites.base.extensions.runContextNotNull
import ekrem.ozan.satellites.base.view.BaseFragment
import ekrem.ozan.satellites.databinding.FragmentDetailBinding

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    override val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) initStartRequest()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelFields()
    }

    private fun initStartRequest() {
        runContextNotNull { context -> viewModel.getData(context, viewModel.satelliteId) }
    }

    private fun observeViewModelFields() {
        observe(viewModel.satelliteDetailLiveData) { binding.viewState = it }
        observe(viewModel.uiStateLiveData) { binding.uiState = it }
    }
}
