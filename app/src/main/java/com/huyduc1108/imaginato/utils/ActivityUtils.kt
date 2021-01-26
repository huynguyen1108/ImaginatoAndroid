package com.huyduc1108.imaginato.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.huyduc1108.imaginato.R
import dagger.internal.Preconditions

object ActivityUtils {
    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     */
    fun pushFragment(
        fragmentManager: FragmentManager?,
        frameId: Int,
        fragment: Fragment
    ) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        Preconditions.checkNotNull(fragment)
        val transaction =
            fragmentManager?.beginTransaction()
//                transaction?.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_exit, R.anim.pop_enter);
        transaction?.add(frameId, fragment)
        transaction?.addToBackStack(fragment.javaClass.name)
        transaction?.commit()
    }

    fun pushFragmentNoBackStack(
        fragmentManager: FragmentManager?,
        frameId: Int,
        fragment: Fragment
    ) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        Preconditions.checkNotNull(fragment)
        val transaction =
            fragmentManager?.beginTransaction()
        if (fragmentManager?.findFragmentByTag(fragment.javaClass.name)?.javaClass?.name == fragment.javaClass.name) {
            for (fragment in fragmentManager!!.fragments) {
                if (fragment.isVisible)
                    transaction?.hide(fragment)
            }
            transaction?.show(fragmentManager.findFragmentByTag(fragment.javaClass.name)!!)
        } else {
            transaction?.add(frameId, fragment, fragment.javaClass.name)
        }
        transaction?.commit()
    }

    fun pushFragmentHasAnimation(
        fragmentManager: FragmentManager?,
        frameId: Int,
        fragment: Fragment
    ) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        Preconditions.checkNotNull(fragment)
        val transaction =
            fragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_in_left,
            R.anim.slide_out_left,
            R.anim.slide_out_right
        )
        transaction?.add(frameId, fragment)
        transaction?.addToBackStack(fragment.javaClass.name)
        transaction?.commit()
    }

    fun replaceByFragment(
        fragmentManager: FragmentManager,
        frameId: Int,
        fragment: Fragment
    ) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        Preconditions.checkNotNull(fragment)
        val transaction =
            fragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
        transaction.replace(frameId, fragment, fragment.tag)
        transaction.addToBackStack(fragment.javaClass.name)
        transaction.commit()
    }

    fun popFragment(fragmentManager: FragmentManager?) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        fragmentManager?.popBackStack()
    }

    fun popAllFragment(fragmentManager: FragmentManager) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        fragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    fun popNFragment(fragmentManager: FragmentManager, n: Int) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        for (i in 0 until n) {
            fragmentManager.popBackStack()
        }
        if (fragmentManager.backStackEntryCount > 0) {
            val fragmentTag =
                fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1)
                    .name
        }
    }
}