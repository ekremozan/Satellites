package ekrem.ozan.satellites.ui.detail

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ekrem.ozan.satellites.R
import ekrem.ozan.satellites.base.view.BaseFragment
import ekrem.ozan.satellites.base.view.BaseViewModel
import ekrem.ozan.satellites.databinding.FragmentDetailBinding

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    override val viewModel: BaseViewModel by viewModels()
}
