package com.vnguy23.mystocktracker.ui.main

import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {
    private var _cardChoice: Int = 1
    private var _codeChoice: Int = 1
    private var _inputChoice: Int = 1

    fun changeCard(opt:Int) {
        _cardChoice = opt
    }

    fun changeCode(opt:Int) {
        _codeChoice = opt
    }

    fun changeInput(opt:Int) {
        _inputChoice = opt
    }

    fun getCard(): Int {
        return _cardChoice
    }

    fun getCode(): Int {
        return _codeChoice
    }

    fun getInput(): Int {
        return _inputChoice
    }
}