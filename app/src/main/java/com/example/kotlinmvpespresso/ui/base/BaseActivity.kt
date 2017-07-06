package com.example.kotlinmvpespresso.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by sally on 7/7/17.
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {
    open var presenter: BasePresenter<*>? = null

    protected abstract fun initializeDagger()

    protected abstract fun initializePresenter()

    abstract var layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initializeDagger()
        initializePresenter()

        presenter?.initialize(intent.extras)

    }
}