package com.example.medapp.ui.fragment.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.medapp.R
import com.example.medapp.components.AppContext
import com.example.medapp.ui.fragment.MenuScreenModule
import com.example.medapp.ui.fragment.menu.adapter.MenuPagerAdapter
import com.example.medapp.ui.other.resources.ResourceProvider
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_menu.*
import javax.inject.Inject

class MenuFragment : Fragment() {

	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	lateinit var viewModel: MenuViewModelInterface

	lateinit var menuPagerAdapter: MenuPagerAdapter

	@Inject
	lateinit var resourceProvider: ResourceProvider

	companion object {
		private const val KEY_PARAMS = "KEY_PARAMS"

		fun newInstance(): MenuFragment {
			return MenuFragment().apply {
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
		return inflater.inflate(R.layout.fragment_menu, container, false)
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
		val component = AppContext.instance.appComponent.plus(MenuScreenModule())
		component.inject(this)
		viewModel = ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]
	}

	//endregion

	//region ===================== UI ======================

	private fun initUI(view: View) {
		menuPagerAdapter = MenuPagerAdapter(this)
		viewPager.adapter = menuPagerAdapter
		TabLayoutMediator(tabLayout, viewPager) { tab, position ->
			tab.text = when (position) {
				0 -> {
					resourceProvider.getString(R.string.menu_end_diffuser_title)
				}

				1 -> {
					resourceProvider.getString(R.string.menu_spherical_diffuser_title)
				}

				2 -> {
					resourceProvider.getString(R.string.menu_cylindrical_diffuser_title)
				}

				else -> {
					resourceProvider.getString(R.string.menu_end_diffuser_title)
				}
			}
		}.attach()
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