package com.example.medapp.ui.widget

import android.os.Parcel
import android.os.Parcelable
import android.util.SparseArray
import android.view.View

class CustomViewGroupSavedState : View.BaseSavedState {
	var childrenStates: SparseArray<Parcelable> = SparseArray<Parcelable>()

	constructor(superState: Parcelable) : super(superState)

	private constructor(inArray: Parcel, classLoader: ClassLoader?) : super(inArray) {
		var sparseArray = SparseArray<Any>()
		sparseArray = inArray.readSparseArray(classLoader) ?: SparseArray<Any>()
		childrenStates = sparseArray as SparseArray<Parcelable>
	}

	override fun writeToParcel(out: Parcel, flags: Int) {
		super.writeToParcel(out, flags)
		out.writeSparseArray(childrenStates as SparseArray<Any>)
	}

	companion object {

		@JvmField
		val CREATOR: Parcelable.ClassLoaderCreator<CustomViewGroupSavedState> =
			object :
				Parcelable.ClassLoaderCreator<CustomViewGroupSavedState> {

				override fun createFromParcel(source: Parcel): CustomViewGroupSavedState {
					return createFromParcel(source, null)
				}

				override fun createFromParcel(
					source: Parcel,
					loader: ClassLoader?
				): CustomViewGroupSavedState {
					return CustomViewGroupSavedState(source, loader)
				}

				override fun newArray(size: Int): Array<CustomViewGroupSavedState?> {
					return arrayOfNulls(size)
				}
			}
	}
}