package com.memtrip.eosreach.app.account.vote.cast.producers

import com.memtrip.mxandroid.MxViewState

data class CastProducersVoteViewState(val view: View) : MxViewState {

    sealed class View {
        object Idle : View()
        object OnProgress : View()
        data class AddProducerField(val nextPosition: Int) : View()
        data class OnError(val message: String, val log: String) : View()
        object OnSuccess : View()
        object NavigateToBlockProducerList : View()
        data class ViewLog(val log: String) : View()
    }
}