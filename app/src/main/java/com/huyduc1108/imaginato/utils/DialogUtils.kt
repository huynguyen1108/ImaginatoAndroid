package com.huyduc1108.imaginato.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.huyduc1108.imaginato.view.dialog.NetWorkDialog
import com.huyduc1108.imaginato.view.dialog.LoadingDialog

inline fun Activity.showNotesAlertDialog(func: NetWorkDialog.() -> Unit): AlertDialog =
    NetWorkDialog(this).apply {
        func()
    }.create()

inline fun Fragment.showNotesAlertDialog(func: NetWorkDialog.() -> Unit): AlertDialog =
    NetWorkDialog(this.requireContext()).apply {
        func()
    }.create()

inline fun Activity.showLoadingAlertDialog(func: LoadingDialog.() -> Unit): AlertDialog =
    LoadingDialog(this).apply {
        func()
    }.create()

inline fun Fragment.showLoadingAlertDialog(func: LoadingDialog.() -> Unit): AlertDialog =
    LoadingDialog(this.requireContext()).apply {
        func()
    }.create()