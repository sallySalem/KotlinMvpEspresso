package com.example.kotlinmvpespresso.ui.base

import android.os.Bundle

/**
 * Created by sally on 7/7/17.
 */
abstract class BasePresenter<T : BaseView> {

    var view: T? = null

    open fun initialize(extras: Bundle?) {}
}