package com.example.medapp.ui.fragment.menu.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.medapp.ui.fragment.cylindricaldiffuser.CylindricalDiffuserFragment
import com.example.medapp.ui.fragment.enddiffuser.EndDiffuserFragment
import com.example.medapp.ui.fragment.sphericaldiffuser.SphericalDiffuserFragment
import com.example.medapp.ui.fragment.sphericaldiffuser.SphericalDiffuserViewModel

class MenuPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
	private val countItem = 3

	override fun getItemCount(): Int {
		return countItem
	}

	override fun createFragment(position: Int): Fragment {
		val fragment = when (position) {
			0 -> {
				return EndDiffuserFragment()
			}

			1 -> {
				return SphericalDiffuserFragment()
			}

			2 -> {
				return CylindricalDiffuserFragment()
			}

			else -> EndDiffuserFragment()
		}

		return fragment
	}
}