package com.example.medapp.ui.widget

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.medapp.R
import com.example.medapp.ui.other.resources.ResourceProviderImpl
import kotlinx.android.synthetic.main.custom_vt_sm_view.view.*

class CustomVtSmView : LinearLayout {

	companion object {
		private const val BORDER_VALUE = 0.4
	}

	//region ===================== Constructors ======================

	constructor(context: Context) : super(context) {
		init(null, 0)
	}

	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
		init(attrs, 0)
	}

	constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
		context,
		attrs,
		defStyle
	) {
		init(attrs, defStyle)
	}

	//endregion

	//region ==================== Lifecycle ====================

	public override fun onSaveInstanceState(): Parcelable? {
		val ss = super.onSaveInstanceState()?.let { CustomViewGroupSavedState(it) }
		ss?.childrenStates = SparseArray()
		for (i in 0 until childCount) {
			getChildAt(i).saveHierarchyState(ss?.childrenStates)
		}
		return ss
	}

	public override fun onRestoreInstanceState(state: Parcelable) {
		val ss = state as CustomViewGroupSavedState
		super.onRestoreInstanceState(ss.superState)
		for (i in 0 until childCount) {
			getChildAt(i).restoreHierarchyState(ss.childrenStates)
		}
	}

	override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>) {
		dispatchFreezeSelfOnly(container)
	}

	override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>) {
		dispatchThawSelfOnly(container)
	}

	//endregion

	//region ===================== Public ======================

	fun setInfo(value: Double) {
		val resourceProvider = ResourceProviderImpl(context)
		tvVtOnSm.text = resourceProvider.getString(R.string.units_format_float_value, value)
		val color = if (value > BORDER_VALUE) {
			ContextCompat.getColor(context, R.color.red_29)
		} else {
			ContextCompat.getColor(context, R.color.green_6C)
		}
		tvVtOnSm.setTextColor(color)
	}

	//endregion

	//region ==================== Internal ====================

	private fun init(attrs: AttributeSet?, defStyle: Int) {
		LayoutInflater.from(context).inflate(R.layout.custom_vt_sm_view, this, true)
	}

	//endregion
}