package com.example.medapp.ui.fragment.sphericaldiffuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.medapp.R
import com.example.medapp.components.AppContext
import com.example.medapp.ui.fragment.enddiffuser.EndDiffuserModule
import com.example.medapp.ui.fragment.enddiffuser.EndDiffuserViewModel
import com.example.medapp.ui.fragment.enddiffuser.EndDiffuserViewModelInterface
import javax.inject.Inject

class SphericalDiffuserFragment : Fragment() {

	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	lateinit var viewModel: SphericalDiffuserViewModelInterface

	companion object {
		private const val KEY_PARAMS = "KEY_PARAMS"

		fun newInstance(): SphericalDiffuserFragment {
			return SphericalDiffuserFragment().apply {
				arguments = Bundle().apply {

				}
			}
		}
	}

	//region ===================== Lifecycle callbacks ======================

	override fun onCreate(savedInstanceState: Bundle?) {
		configureDI()
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_spherical_diffuser, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initUI(view)
	}

	//endregion

	//region ===================== UI handlers ======================

	//endregion

	//region ===================== DI ======================

	private fun configureDI() {
		val component = AppContext.instance.appComponent.plus(SphericalDiffuserModule())
		component.inject(this)
		viewModel = ViewModelProvider(this, viewModelFactory)[SphericalDiffuserViewModel::class.java]
	}

	//endregion

	//region ===================== UI ======================

	private fun initUI(view: View) {
		/*setupToolbar(
			view,
			null as String?,
			R.drawable.ic_back_white_50_percent,
			false,
			btnBackClickListener
		)*/
	}

	//endregion

}