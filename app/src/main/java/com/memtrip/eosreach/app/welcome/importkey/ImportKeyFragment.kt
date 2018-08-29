package com.memtrip.eosreach.app.welcome.importkey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.jakewharton.rxbinding2.view.RxView
import com.memtrip.eosreach.R
import com.memtrip.eosreach.app.MviFragment
import com.memtrip.eosreach.app.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import kotlinx.android.synthetic.main.welcome_import_key_fragment.*
import javax.inject.Inject

internal class ImportKeyFragment
    : MviFragment<ImportKeyIntent, ImportKeyRenderAction, ImportKeyViewState, ImportKeyViewLayout>(), ImportKeyViewLayout {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var render: ImportKeyViewRenderer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.welcome_import_key_fragment, container, false)
    }

    override fun inject() {
        AndroidSupportInjection.inject(this)
    }

    override fun intents(): Observable<ImportKeyIntent> {
        return RxView.clicks(welcome_import_key_import_button).map {
            ImportKeyIntent.ImportKey(welcome_import_key_private_key_value_input.text.toString())
        }
    }

    override fun layout(): ImportKeyViewLayout = this

    override fun model(): ImportKeyViewModel = getViewModel(viewModelFactory)

    override fun render(): ImportKeyViewRenderer = render

    override fun showProgress() {
    }

    override fun success() {
        NavHostFragment.findNavController(this).navigate(
            R.id.welcome_navigation_action_importKey_to_accountsList)
    }

    override fun showError(error: String) {
        welcome_import_key_private_key_value_label.error = error
    }
}
