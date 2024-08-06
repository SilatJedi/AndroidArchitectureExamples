package com.moxielabs.androidarchitectureexamples.mvp

import com.moxielabs.androidarchitectureexamples.shared.models.Fruit

interface MvpContract {
    interface View {
        val presenter: Presenter
        fun displayList(list: List<String>)
    }

    interface Presenter {
        val view: View
        fun fetchList()
    }
}