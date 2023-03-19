package com.example.medapp.ui.fragment.enddiffuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.medapp.R
import com.example.medapp.components.AppContext
import com.example.medapp.ui.fragment.enddiffuser.model.EndDiffuserInputValueType
import kotlinx.android.synthetic.main.custom_input_view.*
import kotlinx.android.synthetic.main.custom_input_view.view.*
import kotlinx.android.synthetic.main.fragment_end_diffuser.*
import javax.inject.Inject

class EndDiffuserFragment : Fragment() {

	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	lateinit var viewModel: EndDiffuserViewModelInterface

	companion object {
		private const val KEY_PARAMS = "KEY_PARAMS"

		fun newInstance(): EndDiffuserFragment {
			return EndDiffuserFragment().apply {
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
		return inflater.inflate(R.layout.fragment_end_diffuser, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		onObservedViewModel()
		initUI(view)
	}

	//endregion

	//region ===================== UI handlers ======================

	//endregion

	//region ===================== DI ======================

	private fun configureDI() {
		val component = AppContext.instance.appComponent.plus(EndDiffuserModule())
		component.inject(this)
		viewModel = ViewModelProvider(this, viewModelFactory)[EndDiffuserViewModel::class.java]
	}

	//endregion

	//region ===================== UI ======================

	private fun initUI(view: View) {
		civLightSpot.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(EndDiffuserInputValueType.DIAMETER_LIGHT_SPOT, value)
			}
			catch (e: NumberFormatException) {
				viewModel.changeInputValue(EndDiffuserInputValueType.DIAMETER_LIGHT_SPOT, null)
			}
		}

		civLaserPower.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(EndDiffuserInputValueType.LASER_POWER, value)
			}
			catch (e: NumberFormatException) {
				viewModel.changeInputValue(EndDiffuserInputValueType.LASER_POWER, null)
			}
		}

		civTreatmentDose.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(EndDiffuserInputValueType.TREATMENT_DOSE, value)
			}
			catch (e: NumberFormatException) {
				viewModel.changeInputValue(EndDiffuserInputValueType.TREATMENT_DOSE, null)
			}
		}

		civEnergyLoss.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(EndDiffuserInputValueType.ENERGY_LOSS, value)
			}
			catch (e: NumberFormatException) {
				viewModel.changeInputValue(EndDiffuserInputValueType.ENERGY_LOSS, null)
			}
		}

		tvCalculate.setOnClickListener {

		}
		/*setupToolbar(
			view,
			null as String?,
			R.drawable.ic_back_white_50_percent,
			false,
			btnBackClickListener
		)*/
	}

	private fun onObservedViewModel() {
		viewModel.enabledButton.observe(viewLifecycleOwner) {
			tvCalculate.isEnabled = it
		}
	}

	//endregion

}