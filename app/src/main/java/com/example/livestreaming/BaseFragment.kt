package com.example.livestreaming

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


abstract class BaseFragment(viewLayout: Int) : Fragment() {
    private var layoutView = viewLayout
    private var rootView:View?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(layoutView, container, false)
        initFragment(rootView!!)
        return rootView
    }

    protected abstract fun initFragment(view: View)
}