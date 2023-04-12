package com.example.medapp.ui.fragment.sphericaldiffuser

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.medapp.R
import com.example.medapp.components.AppContext
import com.example.medapp.ui.fragment.sphericaldiffuser.model.SphericalDiffuserValueType
import com.example.medapp.ui.other.resources.ResourceProvider
import kotlinx.android.synthetic.main.custom_input_view.view.*
import kotlinx.android.synthetic.main.custom_result_view.*
import kotlinx.android.synthetic.main.fragment_spherical_diffuser.*
import kotlinx.android.synthetic.main.fragment_spherical_diffuser.civEnergyLoss
import kotlinx.android.synthetic.main.fragment_spherical_diffuser.civLaserPower
import kotlinx.android.synthetic.main.fragment_spherical_diffuser.civTreatmentDose
import kotlinx.android.synthetic.main.fragment_spherical_diffuser.crvTime
import kotlinx.android.synthetic.main.fragment_spherical_diffuser.llContainerS
import kotlinx.android.synthetic.main.fragment_spherical_diffuser.tvCalculate
import kotlinx.android.synthetic.main.fragment_spherical_diffuser.tvClear
import kotlinx.android.synthetic.main.fragment_spherical_diffuser.tvValueS
import javax.inject.Inject

class SphericalDiffuserFragment : Fragment() {

	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	lateinit var viewModel: SphericalDiffuserViewModelInterface

	@Inject
	lateinit var resourceProvider: ResourceProvider

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
		onObservedViewModel()
		initUI(view)
	}

	//endregion

	//region ===================== UI handlers ======================

	//endregion

	//region ===================== DI ======================

	private fun configureDI() {
		val component = AppContext.instance.appComponent.plus(SphericalDiffuserModule())
		component.inject(this)
		viewModel =
			ViewModelProvider(this, viewModelFactory)[SphericalDiffuserViewModel::class.java]
	}

	//endregion

	//region ===================== UI ======================

	private fun initUI(view: View) {
		crvTime.isVisible = false
		llContainerS.isVisible = false
		civEnergyLoss.etInputValue.setText("0")

		civBubbleVolume.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(SphericalDiffuserValueType.BUBBLE_VOLUME, value)
			} catch (e: NumberFormatException) {
				viewModel.changeInputValue(SphericalDiffuserValueType.BUBBLE_VOLUME, null)
			}
		}

		civLaserPower.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(SphericalDiffuserValueType.LASER_POWER, value)
			} catch (e: NumberFormatException) {
				viewModel.changeInputValue(SphericalDiffuserValueType.LASER_POWER, null)
			}
		}

		civTreatmentDose.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(SphericalDiffuserValueType.TREATMENT_DOSE, value)
			} catch (e: NumberFormatException) {
				viewModel.changeInputValue(SphericalDiffuserValueType.TREATMENT_DOSE, null)
			}
		}

		civEnergyLoss.etInputValue.addTextChangedListener {
			try {
				val value = it.toString().toDouble()
				viewModel.changeInputValue(SphericalDiffuserValueType.ENERGY_LOSS, value)
			} catch (e: NumberFormatException) {
				viewModel.changeInputValue(SphericalDiffuserValueType.ENERGY_LOSS, 0.0)
			}
		}

		civEnergyLoss.etInputValue.setOnFocusChangeListener { view, hasFocus ->
			if (!hasFocus) {
				if (civEnergyLoss.etInputValue.text.isBlank()) {
					civEnergyLoss.etInputValue.setText("0")
				}
			}
		}

		tvCalculate.setOnClickListener {
			it.hideKeyboard()
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
			llContainerS.isVisible = true
			val minute = it.time.minute
			val second = it.time.second
			if (minute != null && second != null) {
				tvMinuteValue.text = resourceProvider.getString(R.string.units_minute, minute)
				tvSecondValue.text = resourceProvider.getString(R.string.units_second, second)
			}
			tvValueS.text = resourceProvider.getString(R.string.units_sm_kv, it.S)
		}

		viewModel.clear.observe(viewLifecycleOwner) {
			civBubbleVolume.etInputValue.text.clear()
			civLaserPower.etInputValue.text.clear()
			civTreatmentDose.etInputValue.text.clear()
			civEnergyLoss.etInputValue.setText("0")
			crvTime.isVisible = false
			llContainerS.isVisible = false
		}
	}

	fun View.hideKeyboard() {
		val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		inputManager.hideSoftInputFromWindow(windowToken, 0)
	}

	//endregion
}