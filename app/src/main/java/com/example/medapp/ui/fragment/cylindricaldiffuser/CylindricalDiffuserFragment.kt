package com.example.medapp.ui.fragment.cylindricaldiffuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.medapp.R
import com.example.medapp.components.AppContext
import com.example.medapp.ui.fragment.cylindricaldiffuser.model.CylindricalDiffuserInputType
import com.example.medapp.ui.other.resources.ResourceProvider
import kotlinx.android.synthetic.main.custom_input_view.view.*
import kotlinx.android.synthetic.main.custom_result_view.*
import kotlinx.android.synthetic.main.fragment_cylindrical_diffuser.*
import javax.inject.Inject

class CylindricalDiffuserFragment : Fragment() {

	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	lateinit var viewModel: CylindricalDiffuserViewModelInterface

	@Inject
	lateinit var resourceProvider: ResourceProvider

	companion object {
		private const val KEY_PARAMS = "KEY_PARAMS"

		fun newInstance(): CylindricalDiffuserFragment {
			return CylindricalDiffuserFragment().apply {
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
		return inflater.inflate(R.layout.fragment_cylindrical_diffuser, container, false)
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
		val component = AppContext.instance.appComponent.plus(CylindricalDiffuserModule())
		component.inject(this)
		viewModel = ViewModelProvider(this, viewModelFactory)[CylindricalDiffuserViewModel::class.java]
	}

	//endregion

	//region ===================== UI ======================

	private fun initUI(view: View) {
		crvTime.isVisible = false
		civLength.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(CylindricalDiffuserInputType.LENGTH, value)
			} catch (e: NumberFormatException) {
				viewModel.changeInputValue(CylindricalDiffuserInputType.LENGTH, null)
			}
		}

		civLaserPower.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(CylindricalDiffuserInputType.LASER_POWER, value)
			} catch (e: NumberFormatException) {
				viewModel.changeInputValue(CylindricalDiffuserInputType.LASER_POWER, null)
			}
		}

		civDistance.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(CylindricalDiffuserInputType.DISTANCE, value)
			} catch (e: NumberFormatException) {
				viewModel.changeInputValue(CylindricalDiffuserInputType.DISTANCE, null)
			}
		}

		civTreatmentDose.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(CylindricalDiffuserInputType.TREATMENT_DOSE, value)
			} catch (e: NumberFormatException) {
				viewModel.changeInputValue(CylindricalDiffuserInputType.TREATMENT_DOSE, null)
			}
		}

		civEnergyLoss.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(CylindricalDiffuserInputType.ENERGY_LOSS, value)
			} catch (e: NumberFormatException) {
				viewModel.changeInputValue(CylindricalDiffuserInputType.ENERGY_LOSS, null)
			}
		}

		tvCalculate.setOnClickListener {
			viewModel.calculate()
		}

		tvClear.setOnClickListener {
			viewModel.clear()
		}
	}

	private fun onObservedViewModel() {
		viewModel.enabledButton.observe(viewLifecycleOwner) {
			tvCalculate.isEnabled = it
		}

		viewModel.result.observe(viewLifecycleOwner) {
			crvTime.isVisible = true
			val minute = it.minute
			val second = it.second
			if (minute != null && second != null) {
				tvMinuteValue.text = resourceProvider.getString(R.string.units_minute, minute)
				tvSecondValue.text = resourceProvider.getString(R.string.units_second, second)
			}
		}

		viewModel.clear.observe(viewLifecycleOwner) {
			civLength.etInputValue.text.clear()
			civLaserPower.etInputValue.text.clear()
			civDistance.etInputValue.text.clear()
			civTreatmentDose.etInputValue.text.clear()
			civEnergyLoss.etInputValue.text.clear()
			crvTime.isVisible = false
		}
	}

	//endregion

}