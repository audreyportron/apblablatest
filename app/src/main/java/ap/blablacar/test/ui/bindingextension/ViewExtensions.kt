package com.publicis.sapient.one.binding

import android.view.View
import androidx.databinding.BindingAdapter
import timber.log.Timber


/**
 * Databinding extension method to avoid using this kind of code inside the XML :
 * ```
 *     android:visibility="@{model.isShown ? View.VISIBLE : View.GONE}"
 * ```
 * Though this notation is valid, it is discouraged to have java code inside the XML, and it's better that the view
 * shall have no reference to the java variables. Then it's easier to have a simple `boolean` set to toggle the
 * visibility of the view, and more readable inside the unit tests of the viewModel.
 */
@BindingAdapter("visible", "whenNotVisible", requireAll = false)
fun setVisible(view: View, visible: Boolean?, whenNotVisible: String?) {
    if (visible == null) {
        Timber.wtf("Missing visible annotation in BindingAdapter")
        return
    }
    view.visibility = if (visible) View.VISIBLE else when (whenNotVisible?.toLowerCase()) {
        "gone", null -> View.GONE
        "invisible" -> View.INVISIBLE
        else -> throw IllegalStateException("Unknown visibility $whenNotVisible")
    }
}