package com.example.medapp.ui.widget

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.medapp.R
import kotlinx.android.synthetic.main.custom_input_view.view.*
import kotlinx.android.synthetic.main.custom_result_view.view.*

class CustomResultView : LinearLayout {

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



	//endregion

	//region ===================== RequestListener ======================

	//endregion

	//region ==================== Internal ====================

	private fun init(attrs: AttributeSet?, defStyle: Int) {
		LayoutInflater.from(context).inflate(R.layout.custom_result_view, this, true)

		if (attrs != null && context != null) {
			val attributes = context.theme.obtainStyledAttributes(
				attrs, R.styleable.CustomResultView, 0, 0
			)

			tvTitle.text = attributes.getText(R.styleable.CustomResultView_title).toString()
		}
	}

	//endregion
}