package com.example.navigationcomponent

import com.example.androidkotlin.R

class Constant {

    object nav_routes {
        const val MAIN_FRAGMENT = R.id.navigationComponentMainFragmen
        const val BANKING_FRAGMENT = R.id.bankingFragment
        const val CHOOSE_AMOUNT_FRAGMENT = R.id.chooseAmountFragment
        const val CHOOSE_RECEPIENT_FRAGMENT = R.id.choseRecepientFragment
        const val VIEW_BALANCE_FRAGMENT = R.id.viewBalanceFragment
    }

    object nav_arguments {
        const val plant_id = "plant_id"
        const val plant_name = "plant_name"
    }

}